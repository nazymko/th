package org.nazymko.thehomeland.parser;

import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.ConnectorConsumerRecord;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.thehomeland.dao.SyncConsumerDao;
import org.nazymko.thehomeland.dao.SyncPageLogDao;
import org.nazymko.thehomeland.parser.db.dao.PageDao;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class Repository {
    @Resource
    private PageDao pageDao;
    @Resource
    private SyncConsumerDao consumerDao;
    @Resource
    private SyncPageLogDao pageLog;

    private static final String MAIN_PAGE_TYPE = "article";

    public Repository() {
    }


    public Result<ThPageRecord> latest(String consumer) {
        Optional<ConnectorConsumerRecord> byDomain = consumerDao.getByConsumer(consumer);
        return latest(consumer, byDomain);

    }

    public Result<ThPageRecord> dirty(String consumer, boolean dirty) {
        Optional<ConnectorConsumerRecord> byDomain = consumerDao.getByConsumer(consumer);
        if (!dirty) {
            return latest(consumer, byDomain);
        } else {
            return pageDao.getByType(MAIN_PAGE_TYPE);

        }
    }

    public Result<ThPageRecord> latest(String consumer, Optional<ConnectorConsumerRecord> byDomain) {
        int latestId = pageLog.getLatestId(consumer);

        if (byDomain.isPresent()) {
            return pageDao.getAfter(latestId, MAIN_PAGE_TYPE);

        } else {
            throw new IllegalArgumentException("Not found '" + consumer + "'");
        }
    }
}
