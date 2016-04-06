package org.nazymko.thehomeland.parser.processors;

import lombok.Data;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nazymko.th.parser.autodao.tables.records.ThAttributeDataRecord;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.th.parser.autodao.tables.records.ThSiteRecord;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.dao.RuleDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.db.model.Attribute;
import org.nazymko.thehomeland.parser.rule.AttrsItem;
import org.nazymko.thehomeland.parser.rule.GroupItem;
import org.nazymko.thehomeland.parser.rule.PageItem;
import org.nazymko.thehomeland.parser.rule.RegexpItem;
import org.nazymko.thehomeland.parser.topology.History;
import org.nazymko.thehomeland.parser.topology.RuleResolver;
import org.nazymko.thehomeland.parser.utils.UrlSimplifier;
import utils.TimeStampHelper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class ParsingTask implements Runnable, InfoSource {
    //Houston, we have a problem
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36";
    @Setter List<AttrListener> listeners = new ArrayList<>();
    @Setter RuleResolver ruleResolver;
    UrlSimplifier simplifier = new UrlSimplifier();
    @Setter private String page, type;
    @Setter private Integer sourcePage, sessionKey;
    @Setter private History historyDao;
    @Setter private SiteDao siteDao;
    @Setter private PageDao pageDao;
    @Setter private RuleDao ruleDao;
    @Setter private Document document;
    private Config config = new Config();
    private PageItem thisPageRule;


    public ParsingTask(String page) {
        this.page = page;
    }

    @Override
    public void run() {
        try {
            log.debug("Start load..{}.", page);
            document = load();

            if (document == null) {
                log.error("Page reading error '{}' : Reason 'Document is null'; ", page);
                return;
            }
            log.debug("After load...");
            log.debug("USED : {}", thisPageRule);
            //TODO : make this logic more clear/rework
            if (thisPageRule.getParent() != null) {
                config.setPageId(pageDao.getById(config.getPageId()).get().getSourcepage());
            }

            for (AttrsItem attr : thisPageRule.getAttrs()) {
                Elements select = document.select(attr.getPath());
                log.debug("Processing '{}'", attr.getPath());

                if (select.size() == 0) {
                    log.debug("Element not found '{}'", attr.getPath());
                    continue;
                } else {
                    //TODO : rework this messy code
                    select.forEach(new Consumer<Element>() {
                                       int index = 0;

                                       @Override
                                       public void accept(Element item) {
                                           String value = getElementValue(item, attr);
                                           processMainAttr(value, attr.isPersist());
                                           if (hasCompositeAttrs(attr)) {
                                               processCompositeAttr(value);
                                           }
                                           index++;
                                       }

                                       private void processCompositeAttr(String value) {
                                           for (RegexpItem regexpItem : attr.getRegexp()) {
                                               log.info("Matching {} : {}", regexpItem.getType(), regexpItem.getExpression());
                                               //can optimize it
                                               //and refactor this
                                               String expression = regexpItem.getExpression();
                                               expression = extend(expression);
                                               Pattern compile = Pattern.compile(expression);
                                               Matcher matcher = compile.matcher(value);
                                               if (matcher.find()) {
                                                   log.info("Matching found for {}", regexpItem.getType());
                                                   MatchResult matchResult = matcher.toMatchResult();
                                                   if (regexpItem.getGroup() == null) {
                                                       log.info("Doing single matching for {}, persist :{}", regexpItem.getType(), regexpItem.isPersist());
                                                       String type = regexpItem.getType();
                                                       ThAttributeDataRecord attribute1 = makeAttrFromConfig(value, null, regexpItem.isPersist(), type, attr.getType());
                                                       log.info("Single matching for {}", regexpItem.getType());
                                                       publish(attribute1, attr.isPersist());
                                                   } else {
                                                       log.info("Doing groups: {}", regexpItem.getType());
                                                       log.info("For value   : {}", value);
                                                       for (GroupItem regExpGroup : regexpItem.getGroup()) {
                                                           String groupValue = matchResult.group(regExpGroup.getOrder());
                                                           log.info("Matched group for {}: {} ", regExpGroup.getType(), groupValue);
                                                           log.info("Persist group : {}", regExpGroup.isPersist());

                                                           ThAttributeDataRecord compositeAttr = makeAttrFromConfig(groupValue, regExpGroup.getFormat(), regExpGroup.isPersist(), regExpGroup.getType(), regExpGroup.getType());
                                                           publish(compositeAttr, regExpGroup.isPersist());
                                                       }
                                                   }
                                               }
                                           }
                                       }

                                       private ThAttributeDataRecord makeAttrFromConfig(String value, String attributeFormat, boolean needPersist, String attributeType, String attributeName) {
                                           ThAttributeDataRecord record = new ThAttributeDataRecord();
                                           record.setPageId(config.getPageId());
                                           record.setRuleId(config.getRuleId());
                                           record.setSiteId(config.getSiteId());

                                           record.setAttributeValue(value);
                                           record.setAttributeType(attributeType);
                                           record.setAttributeName(attributeName);
                                           record.setAttributeFormat(attributeFormat);

                                           record.setAttributeIndex(index);
                                           return record;

                                       }

                                       private boolean hasCompositeAttrs(AttrsItem attr) {
                                           return attr.getRegexp() != null && !attr.getRegexp().isEmpty();
                                       }

                                       private void processMainAttr(String value, boolean persist) {
                                           String _attr = attr.getAttr();

                                           ThAttributeDataRecord attribute = makeAttrFromConfig(value, null, persist, _attr, attr.getType());
                                           publish(attribute, persist);
                                       }

                                       private void publish(ThAttributeDataRecord attribute, boolean persist) {
                                           for (AttrListener listener : listeners) {
                                               if (listener.support(attribute, sessionKey, persist)) {
                                                   listener.process(attribute, sessionKey);
                                               }
                                           }
                                       }

                                       private String extend(String expression) {
                                           return ".*" + expression + ".*";
                                       }
                                   }

                    );
                }
            }
            log.debug("Finished {}.", page);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private String getElementValue(Element item, AttrsItem attr) {
        String value;
        if ("text".equals(attr.getAttr())) {
            value = item.text();
        } else {
            value = item.attr(attr.getAttr());
        }
        return value;
    }

    private Document load() throws MalformedURLException {
        log.debug("Init {}", page);

        String siteUrl = simplifier.getDomainName(this.page);

        Optional<ThSiteRecord> byUrl = siteDao.getByUrl(siteUrl);
        if (!byUrl.isPresent()) {
            log.error("Site Record not found for {}", siteUrl);

        }
        ThSiteRecord site = byUrl.get();
        this.config.setSiteId(site.getId());

        ThPageRecord pageRecord = new ThPageRecord();

        pageRecord.setPageUrl(page);
        pageRecord.setSourcepage(sourcePage);
        pageRecord.setTaskRunId(sessionKey);
        pageRecord.setType(type);
        pageRecord.setAuthority(site.getAuthority());
        pageRecord.setSiteId(config.getSiteId());
        pageRecord.setRegisteredAt(TimeStampHelper.now());

        pageDao.save(pageRecord);
        config.setPageId(pageRecord.getId());

        config.setRuleId(ruleDao.getById(simplifier.authority(siteUrl)).get().getId());

        thisPageRule = ruleResolver.resolveByTypeForSite(siteUrl, type).get();

        Document document = null;
        try {
            log.debug("Connect");
            String link = makeUrl("http://" + siteUrl, page);
            document = Jsoup.connect(link)
                    .userAgent(userAgent)
                    .timeout(config.getTimeout())
                    .execute()
                    .parse();

            historyDao.visitBySession(link, sessionKey);

            log.debug("'{}' parsed", link);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document;
    }

    private String makeUrl(String site, String page) throws MalformedURLException {

        if (this.sourcePage == -1) {
            //Ok, it is root page
            return site;
        } else if (page != null
                && !page.startsWith("http://")
                && !page.startsWith("https://")
                && !page.startsWith("www.")
                ) {
            String result = page;
            result = new URL(new URL(site), page).toExternalForm();
            return result;
        }
        //fine, this page url have a nice look
        return page;
    }

    @Override
    public String info() {
        return null;
    }

    @Override
    public Integer runId() {
        return sessionKey;
    }

    @Data
    class Config {
        int timeout = 10_000;
        private Integer siteId, pageId, ruleId;
    }
}
