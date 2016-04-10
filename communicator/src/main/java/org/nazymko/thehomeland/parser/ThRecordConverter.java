package org.nazymko.thehomeland.parser;

import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class ThRecordConverter extends AbstractConverter {
    private HashMap<Integer, HashMap<String, String>> rulePull;

    public void setRulePull(HashMap<Integer, HashMap<String, String>> rulePull) {
        this.rulePull = rulePull;
    }

    @Override public Map convert(ThPageRecord record) {
        return super.convert(record);
    }

    @Override public HashMap<String, String> getRule(Integer siteId) {
        return rulePull.get(siteId);
    }

}
