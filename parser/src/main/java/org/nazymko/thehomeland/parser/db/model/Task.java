package org.nazymko.thehomeland.parser.db.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Task {
    public Task(int id, int schedule_source_id, Timestamp startAt, Timestamp finishAt, int status, String message) {
        this.id = id;
        this.schedule_source_id = schedule_source_id;
        this.startAt = startAt;
        this.finishAt = finishAt;
        this.status = status;
        this.message = message;
    }

    public Task(int schedule_source_id, Timestamp startAt, Timestamp finishAt, int status, String message) {

        this.schedule_source_id = schedule_source_id;
        this.startAt = startAt;
        this.finishAt = finishAt;
        this.status = status;
        this.message = message;
    }

    int id;
    int schedule_source_id;
    Timestamp startAt;
    Timestamp finishAt;
    int status;
    String message;
}