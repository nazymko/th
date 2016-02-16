package org.nazymko.thehomeland.parser.processors;

import lombok.extern.log4j.Log4j2;
import org.nazymko.thehomeland.parser.db.model.Attribute;

/**
 * Created by nazymko
 */
@Log4j2(topic = "[LOG LISTENER]")
public class LogAttrListener implements AttrListener {

    @Override
    public boolean support(String type) {
        return true;
    }

    @Override
    public void process(Integer pageId, Attribute attribute, Integer runId) {
        log.info("{} listener: {}: " + "current page '{}', attr '{}', value(20 signs) '{}'", threadName(), identifier(), attribute.getPageId(), attribute.getAttrMeaning(), attribute.getAttrValue().substring(0, Math.min(20, attribute.getAttrValue().length())));
    }

    private String threadName() {
        return Thread.currentThread().getName();
    }
}
