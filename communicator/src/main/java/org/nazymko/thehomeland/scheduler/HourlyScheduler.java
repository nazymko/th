package org.nazymko.thehomeland.scheduler;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.ConnectorConsumerRecord;
import org.nazymko.th.parser.autodao.tables.records.ConnectorRulesRecord;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.thehomeland.dao.SyncConsumerDao;
import org.nazymko.thehomeland.dao.SyncHeadersDao;
import org.nazymko.thehomeland.dao.SyncRuleDao;
import org.nazymko.thehomeland.integration.PostMessageChannel;
import org.nazymko.thehomeland.parser.Repository;
import org.nazymko.thehomeland.parser.ThRecordConverter;
import org.nazymko.thehomeland.services.SendingLogService;
import org.nazymko.thehomeland.utils.RuleConverter;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Log4j2
public class HourlyScheduler implements Scheduler {


    @Resource
    PostMessageChannel messageChannel;
    private Gson GSON = new Gson();
    @Resource
    private SyncRuleDao syncRuleDao;

    @Resource
    private ThRecordConverter converter;

    @Resource
    private Repository repository;

    @Resource
    private SyncHeadersDao syncHeadersDao;
    @Resource
    private SyncConsumerDao consumerDao;
    @Resource
    private SendingLogService logService;


    @Override
    public void doIt() {
        //TODO: Throw exception
    }

    public void doIt(String consumer) {
        try {
            log.info("Started");

            converter.init(syncRuleDao.all());

            ConnectorConsumerRecord consumerRecord = consumerDao.getByConsumer(consumer).get();
            Result<ThPageRecord> latest = repository.latest(consumer);


            HashMap<String, Object> headers = getHeaders(consumerRecord.getId());

            log.info("New records for send : {}", latest.size());
            String json = null;
            for (ThPageRecord thPageRecord : latest) {
                Optional<Map> convert = converter.convert(thPageRecord);
                if (!convert.isPresent()) {
                    continue;
                }
                json = new String(GSON.toJson(convert.get()).getBytes(Charset.forName("UTF-8")), "UTF-8");
                System.out.println("json = " + json);
                tryToSend(thPageRecord, json, headers, consumer);
                json = null;
            }
            log.info("Finished");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private HashMap<String, Object> getHeaders(Integer id) {
        return syncHeadersDao.getByConsumer(id);
    }

    private void tryToSend(ThPageRecord thPageRecord, String json, HashMap<String, Object> headers, String consumer) {
        GenericMessage<String> msg = null;
        try {

            HashMap<String, Object> _mh = new HashMap<>(headers);
            _mh.put("X-Original-Page-Id", thPageRecord.getId());

            MessageHeaders messageHeaders = new MessageHeaders(_mh);
            msg = new GenericMessage<>(json, messageHeaders);
            messageChannel.send(msg);
        } catch (HttpClientErrorException ex) {
            log.error("Failed to send. Record id = " + thPageRecord.getId(), ex);
            logService.failedToSend(thPageRecord.getId(), ex.getStatusCode().value(), consumer, json);
        } catch (Exception ex) {
            log.error("Failed to send: thPageRecord = [" + thPageRecord.getId() + "], json = [" + json + "]", ex);
            logService.failedToSend(thPageRecord.getId(), -1, consumer, json);
        }
    }


    public void doIt(String domain, Timestamp from, boolean force) {

    }
}
