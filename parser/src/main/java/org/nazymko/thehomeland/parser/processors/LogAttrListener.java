package org.nazymko.thehomeland.parser.processors;

import lombok.extern.log4j.Log4j2;
import org.nazymko.th.parser.autodao.tables.records.ThAttributeDataRecord;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class LogAttrListener implements AttrListener {

    private String threadName() {
        return Thread.currentThread().getName();
    }

    @Override
    public boolean support(ThAttributeDataRecord attribute, Integer runId, boolean persist) {
        return true;
    }

    @Override
    public void process(ThAttributeDataRecord attribute, Integer runId) {
        log.debug("run id: {}, attribute: \n{}", runId, attribute);
    }

}
