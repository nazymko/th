package org.nazymko.thehomeland.parser.processors;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.nazymko.th.parser.autodao.tables.records.ThAttributeDataRecord;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.thehomeland.parser.THLParserRunner;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;
import org.nazymko.thehomeland.parser.rule.PageItem;
import org.nazymko.thehomeland.parser.topology.RuleResolver;
import org.nazymko.thehomeland.parser.utils.UrlSimplifier;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.Set;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class LinkProcessorListener implements AttrListener {
    Set<String> supportedAttrs;
    @Resource
    THLParserRunner instance;
    @Resource
    UrlSimplifier simplifier;
    @Setter
    @Resource
    private SiteDao siteDao;
    @Resource
    PageDao pageDao;
    @Autowired
    private RuleResolver resolver;


    public void setSupportedAttrs(Set<String> supportedAttrs) {
        this.supportedAttrs = supportedAttrs;
    }

    private String fixUrlfNeed(String site, String page) throws MalformedURLException {
        if (page != null
                && !page.startsWith("http://")
                && !page.startsWith("https://")
                && !page.startsWith("www.")) {
            page = new URL(new URL(simplifier.addProtocol(site)), page).toExternalForm();
        }

        return page;
    }

    @Override
    public boolean support(ThAttributeDataRecord attribute, Integer runId, boolean persist) {
        log.debug("attribute = \n[" + attribute + "]");
        return "href".equals(attribute.getAttributeType());
    }

    @Override
    public void process(ThAttributeDataRecord attribute, Integer runId) {
        try {
            String pageType = attribute.getAttributeName();
            String site = siteDao.getById(attribute.getSiteId()).get().getAuthority();
            String page = fixUrlfNeed(site, attribute.getAttributeValue());
            Optional<PageItem> pageItem = resolver.resolveByAttr(site, pageType);

            if (pageItem.isPresent()) {
                PageItem item = pageItem.get();
                if (item.getUnique()) {
                    Optional<ThPageRecord> thisPage = pageDao.getByUrl(page);
                    if (thisPage.isPresent()) {
                        log.info("Skipping page (already processed as page {}): {}", thisPage.get().getId(), attribute.getAttributeValue());
                        return;
                    }
                }
            }
            instance.schedule(page, pageType, attribute.getPageId(), runId);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
