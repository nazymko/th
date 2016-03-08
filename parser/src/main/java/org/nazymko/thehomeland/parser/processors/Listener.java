package org.nazymko.thehomeland.parser.processors;

import org.nazymko.thehomeland.parser.db.model.Attribute;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public interface Listener {
    boolean support(String type, String attr);

    void process(Integer pageId, Attribute attribute, Integer runId);


}
