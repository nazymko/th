package org.nazymko.thehomeland.parser;

import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.ConnectorConsumerRecord;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.thehomeland.dao.SyncConsumerDao;
import org.nazymko.thehomeland.dao.SyncMainLogDao;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class Repository {
    @Autowired
    private PageDao pageDao;
    @Autowired
    private SyncMainLogDao mainLogDao;
    @Autowired
    private SyncConsumerDao consumerDao;

    public Result<ThPageRecord> latest() {
        String consumer = "http://thehomeland.com.ua";
        Optional<ConnectorConsumerRecord> byDomain = consumerDao.getByDomain(consumer);
        if (byDomain.isPresent()) {
            ConnectorConsumerRecord connectorConsumerRecord = byDomain.get();

            Integer page = mainLogDao.getLastPage(connectorConsumerRecord.getId());

            return pageDao.getAfter(page, "article");

        } else {
            throw new IllegalArgumentException("Not found '" + consumer + "'");
        }

    }
}
