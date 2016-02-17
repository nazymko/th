package org.nazymko.thehomeland.parser.processors;

import lombok.Data;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nazymko.th.parser.autodao.tables.records.PageRecord;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.dao.RuleDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.db.model.Attribute;
import org.nazymko.thehomeland.parser.db.model.Page;
import org.nazymko.thehomeland.parser.rule.AttrsItem;
import org.nazymko.thehomeland.parser.rule.PageItem;
import org.nazymko.thehomeland.parser.topology.History;
import org.nazymko.thehomeland.parser.topology.RuleResolver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class ParserTask implements Runnable, InfoSource {
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36";
    @Setter List<AttrListener> listeners = new ArrayList<>();
    @Setter RuleResolver ruleResolver;
    @Setter private String page, type;
    @Setter private Integer sourcePage, sessionKey;
    @Setter private History historyDao;
    @Setter private SiteDao siteDao;
    @Setter private PageDao pageDao;
    @Setter private RuleDao ruleDao;
    @Setter private Document document;

    private Config config = new Config();
    private PageItem thisPageRule;

    public ParserTask(String page) {
        this.page = page;
    }


    @Override
    public void run() {
        try {
            log.info("Start load..{}.", page);
            document = load();

            if (document == null) {
                log.error("Page reading error '{}' : Reason 'Document is null'; ", page);
                return;
            }
            log.info("After load...");
            String name = "[" + Thread.currentThread().getName() + "] : threadId " + Thread.currentThread().getId();

            for (AttrsItem attr : thisPageRule.getAttrs()) {
                Elements select = document.select(attr.getPath());
                log.info(String.format(name + ": " + "Processing '%s'", attr.getPath()));

                if (select.size() == 0) {
                    log.info(String.format(name + ": " + "Element not found '%s'", attr.getPath()));
                    continue;
                } else {
                    select.forEach(new Consumer<Element>() {
                        int index = 0;

                        @Override
                        public void accept(Element item) {

                            String valueAttribute = attr.getAttr();

                            Attribute attribute = new Attribute();

                            String value = getElementValue(item, attr);

                            attribute.setAttrIndex(index);
                            attribute.setAttrType(valueAttribute);
                            attribute.setAttrMeaning(attr.getType());
                            attribute.setAttrValue(value);
                            attribute.setSiteId(config.getSiteId());
                            attribute.setPageId(config.getPageId());
                            attribute.setRuleId(config.getRuleId());

                            for (AttrListener listener : listeners) {
                                if (listener.support(attr.getType())) {
                                    listener.process(config.getPageId(), attribute, sessionKey);
                                }
                            }

                            index++;
                        }
                    });
                }
            }
            log.info("Finished {}.", page);
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
        log.info("Init {}", page);

        URL url = new URL(this.page);
        String siteUrl = url.getProtocol() + "://" + url.getAuthority();

        Integer siteId = siteDao.getIdByUrl(siteUrl);
        this.config.setSiteId(siteId);

        PageRecord pageRecord = new PageRecord();

        pageRecord.setUrl(page);
        pageRecord.setSourcepage(sourcePage);
        pageRecord.setTaskRunId(sessionKey);
        pageRecord.setType(type);
        pageRecord.setSiteId(config.getSiteId());

        pageDao.save(pageRecord);
        config.setPageId(pageRecord.getId());

        config.setRuleId(ruleDao.get(siteUrl).get().getId());

        thisPageRule = ruleResolver.resolveByTypeForSite(siteUrl, type).get();

        Document document = null;
        try {
            log.info("Connect");
            String link = makeUrl(siteUrl, page);
            document = Jsoup.connect(link)
                    .userAgent(userAgent)
                    .timeout(config.getTimeout())
                    .execute()
                    .parse();

            historyDao.visit(link);

            log.info("'{}' parsed", link);
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

    @Data
    class Config {
        Integer siteId, pageId, ruleId;
        int timeout = 10_000;
    }
}
