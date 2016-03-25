package org.nazymko.thehomeland.parser;

import org.nazymko.th.parser.autodao.tables.records.ConnectorSyncPageLogRecord;
import org.nazymko.th.parser.autodao.tables.records.ThSiteRecord;
import org.nazymko.thehomeland.Trip;
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


    public Dto covert(ThSiteRecord record) {
        List<ConnectorSyncPageLogRecord> all = logDao.all("thehomeland.com.ua");
        logDao.getLatestDate("thehomeland.com.ua");
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
