package org.nazymko.thehomeland.parser.processors;

import org.nazymko.th.parser.autodao.tables.records.ThAttributeDataRecord;
import org.nazymko.thehomeland.parser.db.model.Attribute;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public interface Listener {
    boolean support(ThAttributeDataRecord attribute,Integer runId,boolean persist);

    void process(ThAttributeDataRecord attribute, Integer runId);


}
