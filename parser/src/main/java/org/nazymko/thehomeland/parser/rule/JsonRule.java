package org.nazymko.thehomeland.parser.rule;

/**
 * Created by JacksonGenerator on 15.03.16.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class JsonRule {
    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("name")
    private String name;
    @JsonProperty("selector")
    private String selector;
    @JsonProperty("page")
    private List<PageItem> page;
    @JsonProperty("url")
    private String url;
}