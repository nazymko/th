package org.nazymko.thehomeland.parser.rule;

import com.google.gson.Gson;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class RuleFactory {

    public static JsonRule make(String rule) {
        return new Gson().fromJson(rule, JsonRule.class);
    }
}
