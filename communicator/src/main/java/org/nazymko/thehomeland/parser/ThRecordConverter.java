package org.nazymko.thehomeland.parser;

import org.nazymko.th.parser.autodao.tables.records.ConnectorRulesRecord;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.nazymko.thehomeland.utils.RuleConverter;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class ThRecordConverter extends AbstractConverter {
    private HashMap<Integer, HashMap<String, String>> rulePull;
    @Resource
    PageDao pageDao;

    public void setRulePull(HashMap<Integer, HashMap<String, String>> rulePull) {
        this.rulePull = rulePull;
    }

    public void init(List<ConnectorRulesRecord> all){
        rulePull=prepare(all);
    }

    private HashMap<Integer, HashMap<String, String>> prepare(List<ConnectorRulesRecord> all) {
        HashMap<Integer, HashMap<String, String>> rules = new HashMap<>();
        for (ConnectorRulesRecord connectorRulesRecord : all) {
            rules.put(connectorRulesRecord.getSiteId(), RuleConverter.makeMap(connectorRulesRecord.getRule()));
        }
        return rules;
    }
    @Override public Optional<Map> convert(ThPageRecord record) {
        return super.convert(record);
    }

    @Override public HashMap<String, String> getRule(Integer siteId) {
        return rulePull.get(siteId);
    }

    public Optional<Map> convert(Integer pageId) {
        Optional<ThPageRecord> byId = pageDao.getById(pageId);
        if (byId.isPresent()) {
            return convert(byId.get());
        } else {
            return Optional.empty();
        }

    }
}
