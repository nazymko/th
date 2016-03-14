package org.nazymko.thehomeland.parser.processors;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.nazymko.thehomeland.parser.THLParserRunner;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.db.model.Attribute;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class LinkProcessorListener implements AttrListener {
    Set<String> supportedAttrs;
    @Resource
    THLParserRunner instance;
    @Setter
    @Resource
    private SiteDao siteDao;

    public void setSupportedAttrs(Set<String> supportedAttrs) {
        this.supportedAttrs = supportedAttrs;
    }

    @Override
    public boolean support(String type, String attr) {
        log.info("type = [" + type + "], attr = [" + attr + "]");
        return "href".equalsIgnoreCase(attr);
    }

    @Override
    public void process(Integer sourcePage, Attribute attribute, Integer runId) {

        String pageType = attribute.getAttrMeaning();
        String site = siteDao.get(attribute.getSiteId()).get().getUrl();

        try {
            String page = fixUrlfNeed(site, attribute.getAttrValue());

            instance.schedule(page, pageType, sourcePage, runId);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private String fixUrlfNeed(String site, String page) throws MalformedURLException {
        String result = page;
        if (page != null
                && !page.startsWith("http://")
                && !page.startsWith("https://")
                && !page.startsWith("www.")) {
            result = new URL(new URL(site), page).toExternalForm();
        }

        return result;
    }
}
