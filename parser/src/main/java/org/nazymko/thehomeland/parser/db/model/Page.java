package org.nazymko.thehomeland.parser.db.model;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.net.URI;
import java.sql.Timestamp;

/**
 * Created by nazymko
 */
@ToString
@EqualsAndHashCode(exclude = {"id"})
public class Page {

    public Integer getId() {

        return id;
    }

    public void setSite(String site) {
        this.site = URI.create(site).toString();
    }

    public String getSite() {
        return site == null ? page : site;
    }

    public String getPage() {
        return page;
    }

    public String getType() {
        return type;
    }

    private Integer id;

    public Integer getSourcePage() {
        return sourcePage;
    }

    private Integer sourcePage = -1;
    private String site;
    private String page;
    private String type;
    private Timestamp registered;
    private Timestamp visited;

    public Timestamp getVisited() {
        return visited;
    }

    public Timestamp getRegistered() {
        return registered;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSourcePage(Integer sourcePage) {
        this.sourcePage = sourcePage;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRegistered(Timestamp registered) {
        this.registered = registered;
    }

    public void setVisited(Timestamp visited) {
        this.visited = visited;
    }

    public Page(Integer id, String site, String page, String type, Timestamp time) {
        this.id = id;
        this.site = site;
        this.page = page;
        this.type = type;
        this.visited = time;
    }

    public Page(Integer id, String site, String page, String type, Timestamp visitedAt, Timestamp registeredAt) {
        this.id = id;
        this.site = site;
        this.page = page;
        this.type = type;
        this.visited = visitedAt;
        this.registered = registeredAt;
    }

    public Page() {
    }

    public Page(String site, String page, String type) {
        this.site = site;
        this.page = page;
        this.type = type;
    }

    public Page(String site, String page, String type, Integer sourcePage) {
        this.site = site;
        this.page = page;
        this.type = type;
        this.sourcePage = sourcePage;
    }
}
