package org.nazymko.thehomeland.scheduler;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.ConnectorRulesRecord;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.thehomeland.dao.SyncRuleDao;
import org.nazymko.thehomeland.integration.PostMessageChannel;
import org.nazymko.thehomeland.parser.Repository;
import org.nazymko.thehomeland.parser.ThRecordConverter;
import org.nazymko.thehomeland.utils.RuleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class HourlyScheduler implements Scheduler {


    @Autowired
    PostMessageChannel messageChannel;
    @Qualifier("connectorRuleDao")
    @Autowired
    private SyncRuleDao ruleDao;
    @Qualifier("thConverter")
    @Autowired
    private ThRecordConverter thRecordConverter;
    @Qualifier("revizor")
    @Autowired
    private Repository repository;

    //    @Scheduled(cron = "0 */1 * * *") - every hour
//    @Scheduled(cron = "0 */1 * * * *") //every minute
    public void doIt() {
        log.info("Started");
        HashMap<Integer, HashMap<String, String>> rules = prepare(ruleDao.all());
        thRecordConverter.setRulePull(rules);
        Result<ThPageRecord> latest = repository.latest();
        for (ThPageRecord thPageRecord : latest) {
            Map convert = thRecordConverter.convert(thPageRecord);
            String string = new Gson().toJson(convert);
            System.out.println("json = " + string);
            messageChannel.send(convert);
        }
        log.info("Finished");
    }

    private HashMap<Integer, HashMap<String, String>> prepare(List<ConnectorRulesRecord> all) {
        HashMap<Integer, HashMap<String, String>> rules = new HashMap<>();
        for (ConnectorRulesRecord connectorRulesRecord : all) {
            rules.put(connectorRulesRecord.getSiteId(), RuleConverter.makeMap(connectorRulesRecord.getRule()));
        }


        return rules;
    }

    public void doIt(String domain) {

    }

    public void doIt(String domain, Timestamp from, boolean force) {

    }
}
