package org.nazymko.thehomeland.parser.rule;

/**
 * Created by JacksonGenerator on 03.03.16.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PageItem {
    @JsonProperty("type")
    private String type;
    @JsonProperty("url")
    private String url;
    @JsonProperty("attrs")
    private List<AttrsItem> attrs;
}