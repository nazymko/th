package org.nazymko.thehomeland.parser.rule;

import lombok.Data;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Data
public class RuleMeta {

    boolean allowDuplicates = false;//visit once

    private String name;
    private String pathProvider;
    private String url;
    private HashMap<String, List<Timestamp>> history = new HashMap<>();

    public boolean add(String link) {
        boolean needToProcess = !history.containsKey(link);
        if (needToProcess) {

            LinkedList<Timestamp> list = new LinkedList<>();

            list.add(new Timestamp(System.currentTimeMillis()));

            history.put(link, list);

        }

        return needToProcess;

    }
}
