package org.nazymko.thehomeland.utils;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by nazymko.patronus@gmail.com
 */
public class RuleConverter {
    public static HashMap<String, String> makeMap(String rule) {
        return new Gson().fromJson(rule, HashMap.class);
    }
}
