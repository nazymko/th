package org.nazymko.thehomeland.parser.processors.special;

/**
 * Created by nazymko
 */
public interface SpecialProcessor {

    void process(Integer pageId, String attr, String pageType, String value);

    boolean support(String pageType, String attrType);

}
