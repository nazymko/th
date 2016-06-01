package org.nazymko.thehomeland.parser.processors;

import org.nazymko.th.parser.autodao.tables.records.ThAttributeDataRecord;
import org.nazymko.thehomeland.parser.db.dao.SiteDao;

import javax.annotation.Resource;

/**
 * Created by nazymko.patronus@gmail.com
 */
public class EnchanceImagesPath implements AttrListener {
    @Resource
    SiteDao siteDao;

    @Override
    public boolean support(ThAttributeDataRecord attribute, Integer runId, boolean persist) {
        return persist;
    }

    @Override
    public void process(ThAttributeDataRecord attribute, Integer runId) {
        if (attribute.getAttributeValue().contains("src=\"/")) {
            String defaultUrl = siteDao.getById(attribute.getSiteId()).get().getDefaultUrl();
            attribute.setAttributeValue(attribute.getAttributeValue().replace("src=\"/", "src=\"" + defaultUrl + "/"));
        }


    }
}
