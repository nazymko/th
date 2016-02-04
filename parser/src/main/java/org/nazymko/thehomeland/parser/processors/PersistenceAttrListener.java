package org.nazymko.thehomeland.parser.processors;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.nazymko.thehomeland.parser.db.dao.AttributeDao;
import org.nazymko.thehomeland.parser.db.model.Attribute;

import javax.annotation.Resource;

/**
 * Created by nazymko
 */
@Log4j2
public class PersistenceAttrListener implements AttrListener {
    @Setter
    @Resource
    private AttributeDao attributeDao;

    @Override
    public boolean support(String type) {
        return true;//because of persisting every attribute
    }

    @Override
    public void process(Integer pageId, Attribute attribute) {
        log.debug(name() + ": " + "pageId = " + pageId);

        attributeDao.save(attribute);


    }

    private String name() {
        return "[" + Thread.currentThread().getName() + "] : " + Thread.currentThread().getId();
    }
}
