package org.nazymko.thehomeland.parser.processors;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.nazymko.thehomeland.parser.THLParser;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.db.model.Attribute;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

/**
 * Created by nazymko
 */
@Log4j2
public class LinkProcessorListener implements AttrListener {
    public void setSupportedAttrs(Set<String> supportedAttrs) {
        this.supportedAttrs = supportedAttrs;
    }

    Set<String> supportedAttrs;

    @Setter
    @Resource
    private SiteDao siteDao;
    @Resource
    THLParser instance;

    @Override
    public boolean support(String type) {
        log.info("type = [" + type + "]");

        if (supportedAttrs != null) {
            return supportedAttrs.contains(type);
        } else {
            log.warn("Supported list is not set.");
            return false;
        }
    }

    @Override
    public void process(Integer sourcePage, Attribute attribute) {

        String site = siteDao.get(attribute.getSiteId()).get().getUrl();
        String link = attribute.getAttrValue();
        String attrMeaning = attribute.getAttrMeaning();

        try {
            String newPage = makeUrl(site, link);
            instance.create(site, newPage, attrMeaning, sourcePage);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private String makeUrl(String site, String page) throws MalformedURLException {
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
