package org.nazymko.thehomeland.parser.processors;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.nazymko.th.parser.autodao.tables.records.ThAttributeDataRecord;
import org.nazymko.thehomeland.parser.db.dao.AttributeDao;
import org.nazymko.thehomeland.parser.db.model.Attribute;

import javax.annotation.Resource;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class PersistenceAttrListener implements AttrListener {
    @Setter
    @Resource
    private AttributeDao attributeDao;

    @Override
    public boolean support(ThAttributeDataRecord attribute, Integer runId, boolean persist) {
        return persist;
    }

    @Override
    public void process(ThAttributeDataRecord attribute, Integer runId) {
        log.debug("saving into db pageId = " + attribute.getPageId());

        attributeDao.save(attribute);

    }
}
