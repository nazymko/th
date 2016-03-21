package org.nazymko.thehomeland.parser;

import org.nazymko.th.parser.autodao.tables.records.ThSiteRecord;
import org.nazymko.thehomeland.Trip;
import org.nazymko.thehomeland.dao.SyncLogDao;
import org.nazymko.thehomeland.parser.db.dao.AttributeDao;
import org.nazymko.thehomeland.parser.db.dao.PageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by nazymko.patronus@gmail.com.
 */
public class TripExtractor implements Converter<ThSiteRecord> {

    @Autowired
    SyncLogDao syncLogDao;
    @Qualifier("pageDao")
    @Autowired
    private PageDao dao;
    @Qualifier("attributeDao")
    @Autowired
    private AttributeDao attributeDao;

    public Dto covert(ThSiteRecord record) {


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
