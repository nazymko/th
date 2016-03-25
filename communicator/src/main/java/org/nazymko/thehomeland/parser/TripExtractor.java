package org.nazymko.thehomeland.parser;

import org.jooq.Result;
import org.nazymko.th.parser.autodao.tables.records.ConnectorSyncPageLogRecord;
import org.nazymko.th.parser.autodao.tables.records.ThPageRecord;
import org.nazymko.th.parser.autodao.tables.records.ThSiteRecord;
import org.nazymko.thehomeland.Trip;
import org.nazymko.thehomeland.dao.SyncMainLogDao;
import org.nazymko.thehomeland.dao.SyncPageLogDao;
import org.nazymko.thehomeland.parser.db.dao.AttributeDao;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class TripExtractor implements Converter<ThSiteRecord> {

    @Qualifier("pageDao")
    @Autowired
    private PageDao pageDao;

    @Qualifier("attributeDao")
    @Autowired
    private AttributeDao attributeDao;

    @Autowired private SyncPageLogDao logDao;
    @Qualifier("mainLogDao")
    @Autowired
    private SyncMainLogDao mainLogDao;


    public Dto covert(ThSiteRecord record) {
        String consumer = "thehomeland.com.ua";
        List<ConnectorSyncPageLogRecord> all = logDao.all(consumer);
        Long lastPage = mainLogDao.getLastPage(consumer);

        Result<ThPageRecord> after = pageDao.getAfter(Math.toIntExact(lastPage), consumer);
        //TODO: from here

        logDao.getLatestDate(consumer);
        Trip build = Trip.builder()
                .departureCityId("Винница")
                .description("Поездочка")
                .dateStart("Сегодня")
                .image("http://some.img")
                .price("500")
                .ticketsTotal("20")
                .title("Назва поїздки")
                .typeId("Тип")
                .targetCityId("Львів")
                .build();

        return build;
    }
}
