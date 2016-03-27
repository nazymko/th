package org.nazymko.thehomeland.parser.db.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Data
public class Schedule {

    int id;
    int siteId;
    String startPage;
    String pageType;
    Timestamp startDate;
    String period;



}
