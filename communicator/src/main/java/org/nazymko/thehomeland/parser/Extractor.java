package org.nazymko.thehomeland.parser;

import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.ConnectorSyncPageLogRecord;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.th.parser.autodao.tables.records.ThSiteRecord;
import org.nazymko.thehomeland.dao.SyncConsumerDao;
import org.nazymko.thehomeland.dao.SyncMainLogDao;
import org.nazymko.thehomeland.dao.SyncPageLogDao;
import org.nazymko.thehomeland.parser.db.dao.AttributeDao;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class Extractor {
    private static final Integer BATCH_SIZE = 10;
    @Autowired private PageDao pageDao;
    @Autowired private AttributeDao attributeDao;
    @Autowired private SyncPageLogDao logDao;
    @Autowired private SyncMainLogDao mainLogDao;
    @Autowired private SyncConsumerDao consumerDao;

    public Dto covert(ThSiteRecord record) {
        String consumer = "thehomeland.com.ua";
        List<ConnectorSyncPageLogRecord> all = logDao.all(consumer);
        Long lastPage = mainLogDao.getLastPage(consumer);

        Result<ThPageRecord> after = pageDao.getAfter(Math.toIntExact(lastPage), consumer);
        //TODO: from here

        List<List<ThPageRecord>> pages = new ArrayList<List<ThPageRecord>>();
        int pos = 0;
        while (pos < after.size()) {
            pages.add(after.subList(pos, pos + BATCH_SIZE));
            pos += BATCH_SIZE;
        }


        logDao.getLatestDate(consumer);

        return null;
    }
}
