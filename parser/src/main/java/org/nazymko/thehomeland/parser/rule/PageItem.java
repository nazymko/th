package org.nazymko.thehomeland.parser.rule;

/**
 * Created by JacksonGenerator on 15.03.16.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PageItem {
    @JsonProperty("parent")
    private String parent;
    @JsonProperty(value = "unique", defaultValue = "false")
    private Boolean unique = false;
    @JsonProperty("type")
    private String type;
    @JsonProperty("attrs")
    private List<AttrsItem> attrs;
}