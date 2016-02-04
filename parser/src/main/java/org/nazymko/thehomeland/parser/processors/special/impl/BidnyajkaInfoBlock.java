package org.nazymko.thehomeland.parser.processors.special.impl;

import org.nazymko.thehomeland.parser.processors.special.SpecialProcessor;

/**
 * Created by nazymko
 */
public class BidnyajkaInfoBlock implements SpecialProcessor {
    String pageType;
    String attrType;

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public void setPageType(String type) {
        this.pageType = type;
    }

    @Override
    public void process(Integer pageId, String attr, String pageType, String value) {

    }

    @Override
    public boolean support(String pageType, String attrType) {
        return this.pageType.equals(pageType) && this.attrType.equals(attrType);
    }
}
