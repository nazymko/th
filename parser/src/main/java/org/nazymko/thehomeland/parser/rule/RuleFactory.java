package org.nazymko.thehomeland.parser.rule;

import com.google.gson.Gson;
import org.nazymko.th.parser.autodao.tables.records.RuleRecord;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class RuleFactory {

    public static ParsingRule make(String rule) {
        return new Gson().fromJson(rule, ParsingRule.class);
    }

    public static ParsingRule make(RuleRecord ruleRecord) {
        ParsingRule rule = make(ruleRecord.getRule());
        rule.setVersion(ruleRecord.getVersion());
        rule.setId(ruleRecord.getId());
        return rule;
    }
}
