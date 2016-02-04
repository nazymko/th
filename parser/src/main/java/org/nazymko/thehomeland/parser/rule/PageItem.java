package org.nazymko.thehomeland.parser.rule;

/**
 * Created by JacksonGenerator on 02.12.15.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageItem implements Serializable {
    @JsonProperty("type")
    private String type;
    @JsonProperty("url")
    private String url;
    @JsonProperty("attrs")
    private List<AttrsItem> attrs;

}