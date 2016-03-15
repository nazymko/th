package org.nazymko.thehomeland.parser.rule;

/**
 * Created by JacksonGenerator on 15.03.16.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AttrsItem {
    @JsonProperty("path")
    private String path;
    @JsonProperty("regexp")
    private List<RegexpItem> regexp;
    @JsonProperty("persist")
    private boolean persist;
    @JsonProperty("attr")
    private String attr;
    @JsonProperty("type")
    private String type;
}