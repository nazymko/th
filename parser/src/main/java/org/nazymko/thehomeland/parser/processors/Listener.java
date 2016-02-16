package org.nazymko.thehomeland.parser.processors;

import org.nazymko.thehomeland.parser.db.model.Attribute;

/**
 * Created by nazymko
 */
public interface Listener {
    boolean support(String type);

    void process(Integer pageId, Attribute attribute, Integer runId);


}
