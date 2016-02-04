package org.nazymko.thehomeland.parser.rule;

/**
 * Created by JacksonGenerator on 02.12.15.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.util.List;

@Data
public class JsonRule implements Serializable {
    transient Integer id = -1;

    @JsonProperty("name")
    private String name;
    @JsonProperty("path_provider")
    private String path_provider;
    @JsonProperty("page")
    private List<PageItem> page;
    @JsonProperty("url")
    private String url;
    @JsonProperty("root")
    private String root;

    public String getUrl() {
        return "http://" + URI.create(url).getAuthority();
    }
}