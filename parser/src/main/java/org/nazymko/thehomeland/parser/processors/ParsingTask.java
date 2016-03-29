package org.nazymko.thehomeland.parser.processors;

import lombok.Data;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
            String name = "[" + Thread.currentThread().getName() + "] : threadId " + Thread.currentThread().getId();
            log.debug("USED : {}", thisPageRule);
            for (AttrsItem attr : thisPageRule.getAttrs()) {
                Elements select = document.select(attr.getPath());
                log.debug(String.format(name + ": " + "Processing '%s'", attr.getPath()));

                if (select.size() == 0) {
                    log.debug(String.format(name + ": " + "Element not found '%s'", attr.getPath()));
                    continue;
                } else {
                    select.forEach(new Consumer<Element>() {
                                       int index = 0;

                                       @Override
                                       public void accept(Element item) {
                                           String value = getElementValue(item, attr);
                                           processMainAttr(item, value);
                                           if (hasCompositeAttrs()) {
                                               processCompositeAttr(item, value);
                                           }
                                           index++;
                                       }

                                       private void processCompositeAttr(Element attribute, String value) {
                                           for (RegexpItem regexpItem : attr.getRegexp()) {
                                               //can optimize it
                                               //and refactor this
                                               String expression = regexpItem.getExpression();
                                               expression = extend(expression);
                                               Pattern compile = Pattern.compile(expression);
                                               Matcher matcher = compile.matcher(value);
                                               if (matcher.find()) {
                                                   MatchResult matchResult = matcher.toMatchResult();
                                                   if (regexpItem.getGroup() == null) {
                                                       String type = regexpItem.getType();
                                                       Attribute attribute1 = makeAttrFromConfig(value, attr.isPersist(), type, null);
                                                       publish(attribute1);
                                                   } else {
                                                       for (GroupItem regExpGroup : regexpItem.getGroup()) {
                                                           String group = matchResult.group(regExpGroup.getOrder());
                                                           Attribute compositeAttr = makeAttrFromConfig(group, regExpGroup.isPersist(), regExpGroup.getType(), regExpGroup.getFormat());
                                                           publish(compositeAttr);
                                                       }
                                                   }
                                               }
                                           }
                                       }

                                       private Attribute makeAttrFromConfig(String group, boolean persist, String tag, String format) {
                                           return Attribute.builder()
                                                   .siteId(config.siteId)
                                                   .attrIndex(index)
                                                   .attrValue(group)
                                                   .attrFormat(format)
                                                   .attrTag(tag)
                                                   .attrMeaning(attr.getType())
                                                   .pageId(config.getPageId())
                                                   .ruleId(config.getRuleId())
                                                   .persistable(persist)
                                                   .build();
                                       }

                                       private boolean hasCompositeAttrs() {
                                           return attr.getRegexp() != null && !attr.getRegexp().isEmpty();
                                       }

                                       private void processMainAttr(Element item, String value) {
                                           String _attr = attr.getAttr();

                                           boolean persist = attr.isPersist();
                                           Attribute attribute = makeAttrFromConfig(value, persist, _attr, null);
                                           publish(attribute);
                                       }

                                       private void publish(Attribute attribute) {
                                           for (AttrListener listener : listeners) {
                                               if (listener.support(attr.getType(), attr.getAttr())) {
                                                   listener.process(config.getPageId(), attribute, sessionKey);
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
            log.error("Not found for {}", siteUrl);

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
        Integer siteId, pageId, ruleId;
        int timeout = 10_000;
    }
}
