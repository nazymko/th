package org.nazymko.thehomeland.parser.processors;

import lombok.Data;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nazymko.th.parser.autodao.tables.records.PageRecord;
import org.nazymko.th.parser.autodao.tables.records.SiteRecord;
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

import javax.annotation.Resource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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
                                               String expression = regexpItem.getExpression();
                                               expression = extend(expression);
                                               Pattern compile = Pattern.compile(expression);
                                               String[] split = value.split("\n");
                                               for (String line : split) {
                                                   Matcher matcher = compile.matcher(line);
                                                   if (matcher.find()) {
                                                       MatchResult matchResult = matcher.toMatchResult();
                                                       if (regexpItem.getGroup() == null) {
                                                           Attribute compositeAttr = Attribute.builder()
                                                                   .siteId(config.siteId)
                                                                   .attrIndex(index)
                                                                   .attrValue(value)
                                                                   .attrType(attr.getAttr())
                                                                   .attrMeaning(regexpItem.getType())
                                                                   .pageId(config.getPageId())
                                                                   .ruleId(config.getRuleId())
                                                                   .persistable(attr.isPersist())
                                                                   .build();
                                                           publish(compositeAttr);
                                                       } else {
                                                           for (GroupItem regExpGroup : regexpItem.getGroup()) {
                                                               String group = matchResult.group(regExpGroup.getOrder());
                                                               log.debug("composite attr " + regExpGroup.getOrder() + ":" + group);


                                                               Attribute compositeAttr = Attribute.builder()
                                                                       .siteId(config.siteId)
                                                                       .attrIndex(index)
                                                                       .attrValue(group)
                                                                       .attrFormat(regExpGroup.getFormat())
                                                                       .attrType(attr.getAttr())
                                                                       .attrMeaning(regExpGroup.getType())
                                                                       .pageId(config.getPageId())
                                                                       .ruleId(config.getRuleId())
                                                                       .persistable(attr.isPersist())
                                                                       .build();
                                                               publish(compositeAttr);
                                                           }
                                                       }
                                                   }
                                               }
                                           }
                                       }

                                       private boolean hasCompositeAttrs() {
                                           return attr.getRegexp() != null && !attr.getRegexp().isEmpty();
                                       }

                                       private void processMainAttr(Element item, String value) {
                                           String type = attr.getAttr();

                                           Attribute attribute = new Attribute();


                                           attribute.setAttrIndex(index);
                                           attribute.setAttrType(type);
                                           attribute.setAttrMeaning(attr.getType());
                                           attribute.setAttrValue(value);
                                           attribute.setSiteId(config.getSiteId());
                                           attribute.setPageId(config.getPageId());
                                           attribute.setRuleId(config.getRuleId());
                                           attribute.setPersistable(attr.isPersist());
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

        String siteUrl = simplifier.simplify(this.page);


/*java.util.NoSuchElementException: No value present
00:09:14.010 [pool-2-thread-1] DEBUG org.nazymko.thehomeland.parser.processors.ParsingTask - Init https://tcb.vn.ua/?p=8118
	at java.util.Optional.get(Optional.java:135)
00:09:14.016 [pool-2-thread-1] DEBUG org.nazymko.thehomeland.parser.processors.ParsingTask - Start load..https://tcb.vn.ua/%D0%B4%D1%80%D0%B0%D0%B3%D0%BE%D0%B1%D1%80%D0%B0%D1%824-%D0%B4%D0%BD%D1%96-24-27-%D0%B1%D0%B5%D1%80%D0%B5%D0%B7%D0%BD%D1%8F-2016-%D0%BD%D0%B8%D0%B7%D1%8C%D0%BA%D0%B8%D0%B9-%D1%81%D0%B5%D0%B7/.
00:09:14.017 [pool-2-thread-1] DEBUG org.nazymko.thehomeland.parser.processors.ParsingTask - Init https://tcb.vn.ua/%D0%B4%D1%80%D0%B0%D0%B3%D0%BE%D0%B1%D1%80%D0%B0%D1%824-%D0%B4%D0%BD%D1%96-24-27-%D0%B1%D0%B5%D1%80%D0%B5%D0%B7%D0%BD%D1%8F-2016-%D0%BD%D0%B8%D0%B7%D1%8C%D0%BA%D0%B8%D0%B9-%D1%81%D0%B5%D0%B7/
	at org.nazymko.thehomeland.parser.processors.ParsingTask.load(ParsingTask.java:163)
	at org.nazymko.thehomeland.parser.processors.ParsingTask.run(ParsingTask.java:66)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
java.util.NoSuchElementException: No value present
	at java.util.Optional.get(Optional.java:135)
	at org.nazymko.thehomeland.parser.processors.ParsingTask.load(ParsingTask.java:163)
	at org.nazymko.thehomeland.parser.processors.ParsingTask.run(ParsingTask.java:66)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
java.util.NoSuchElementException: No value present
	at java.util.Optional.get(Optional.java:135)
	at org.nazymko.thehomeland.parser.processors.ParsingTask.load(ParsingTask.java:163)
	at org.nazymko.thehomeland.parser.processors.ParsingTask.run(ParsingTask.java:66)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)

	*/
        Optional<SiteRecord> byUrl = siteDao.getByUrl(siteUrl);
        if (!byUrl.isPresent()) {
            log.error("Not found for {}", siteUrl);
        }
        SiteRecord site = byUrl.get();
        this.config.setSiteId(site.getId());

        PageRecord pageRecord = new PageRecord();

        pageRecord.setUrl(page);
        pageRecord.setSourcepage(sourcePage);
        pageRecord.setTaskRunId(sessionKey);
        pageRecord.setType(type);
        pageRecord.setSiteUrl(site.getUrl());
        pageRecord.setSiteId(config.getSiteId());
        pageRecord.setRegisteredAt(TimeStampHelper.now());

        pageDao.save(pageRecord);
        config.setPageId(pageRecord.getId());

        config.setRuleId(ruleDao.get(siteUrl).get().getId());

        thisPageRule = ruleResolver.resolveByTypeForSite(siteUrl, type).get();

        Document document = null;
        try {
            log.debug("Connect");
            String link = makeUrl(siteUrl, page);
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
        String result = page;
        if (page != null
                && !page.startsWith("http://")
                && !page.startsWith("https://")
                && !page.startsWith("www.")
                ) {
            result = new URL(new URL(site), page).toExternalForm();
        }

        return result;
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
