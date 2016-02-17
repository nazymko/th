package org.nazymko.thehomeland.parser.db.model;

import lombok.Data;

/**
 * Created by nazymko.patronus@gmail.com.
 */
@Data
public class Site {
    private Integer id;
    private String url;
    private String name;

    public Site() {
    }
    public Site(String url, String name) {
        this.url = url;
        this.name = name;
    }
}
