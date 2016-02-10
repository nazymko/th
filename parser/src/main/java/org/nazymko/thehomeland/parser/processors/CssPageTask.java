package org.nazymko.thehomeland.parser.processors;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nazymko.thehomeland.parser.THLParser;
import org.nazymko.thehomeland.parser.db.dao.AttributeDao;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.dao.RuleDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.db.model.Attribute;
import org.nazymko.thehomeland.parser.db.model.Page;
import org.nazymko.thehomeland.parser.rule.AttrsItem;
import org.nazymko.thehomeland.parser.rule.JsonRule;
import org.nazymko.thehomeland.parser.rule.PageItem;
import org.nazymko.thehomeland.parser.topology.History;
import org.nazymko.thehomeland.parser.topology.RuleResolver;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by nazymko
 */
@Log4j2
public class CssPageTask implements Runnable, InfoSource {

    List<AttrListener> listeners = new ArrayList<>();
    private History historyDao;

    @Setter
    @Resource
    RuleResolver ruleResolver;
    @Setter
    @Resource
    private SiteDao siteDao;
    private JsonRule rule;
    private Integer siteId = -1;
    private Integer pageId = -1;
    private Integer ruleId = -1;
    @Setter
    @Resource
    private PageDao pageDao;
    @Setter
    @Resource
    private RuleDao ruleDao;
    @Setter
    @Resource
    private AttributeDao attributeDao;
    private Document document;
    private String page;
    private String type;
    private String site;


    public static final int timeout = 10_000;

    public CssPageTask(String page, List<AttrListener> listeners) {
        this.listeners = new ArrayList<>(listeners);
        this.page = page;
    }

    public CssPageTask(String page) {
        this.page = page;
    }

    public CssPageTask(int pageId) {
        this.pageId = pageId;
    }

    public CssPageTask(String page, List<AttrListener> listeners, History historyDao) {

        this.page = page;
        this.listeners = listeners;
        this.historyDao = historyDao;
    }


    @Override
    public void run() {
        try {
            log.info("Started..{}.", page);
            init();

            if (document == null) {
                log.error("Page reading error '{}' : Reason 'Document is null'; ", page);
                return;
            }
            log.info("After init...");
            Thread.currentThread().setName("CssPageTask");

            String name = "[" + Thread.currentThread().getName() + "] : threadId " + Thread.currentThread().getId();
            log.info(name + ": " + "Starting process :" + page);
            log.info(name + ": " + "Page rule:" + rule);

            PageItem thisPageDescription = ruleResolver.resolveByTypeForSite(site, type).get();

            List<Attribute> data = new LinkedList<>();

            for (AttrsItem attr : thisPageDescription.getAttrs()) {
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

                            String valueAttribute = attr.getAttr();

                            Attribute attribute = new Attribute();

                            String value = getValue(item, attr);

                            attribute.setAttrIndex(index);
                            attribute.setAttrType(valueAttribute);
                            attribute.setAttrMeaning(attr.getType());
                            attribute.setAttrValue(value);
                            attribute.setSiteId(siteId);
                            attribute.setPageId(pageId);
                            attribute.setRuleId(ruleId);

                            for (AttrListener listener : listeners) {
                                if (listener.support(attr.getType())) {
                                    listener.process(pageId, attribute);
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

    private String getValue(Element item, AttrsItem attr) {
        String value;
        if ("text".equals(attr.getAttr())) {
            value = item.text();
        } else {
            value = item.attr(attr.getAttr());
        }
        return value;
    }

    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36";

    private void init() {
        log.info("Init");
        Page page;
        if (this.page != null) {
            Optional<Page> page1 = pageDao.get(this.page);
            page = page1.get();
        } else {
            page = pageDao.getById(pageId).get();
        }

        this.site = page.getSite();
        this.siteId = siteDao.getIdByUrl(site);
        this.pageId = page.getId();
        this.type = page.getType();
        this.rule = ruleDao.get(site).get();
        this.ruleId = rule.getId();

        try {
            log.info("Connect");
            String link = makeUrl(page.getSite(), page.getPage());
            this.document = Jsoup.connect(link)
                    .userAgent(userAgent)
                    .timeout(timeout)
                    .execute()
                    .parse();

            historyDao.visit(link);

            log.info("'{}' parsed", link);
        } catch (IOException e) {
            e.printStackTrace();
        }


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
}
