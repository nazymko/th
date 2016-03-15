package org.nazymko.thehomeland.parser.rule;

/**
 * Created by JacksonGenerator on 15.03.16.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GroupItem {
    @JsonProperty("nature")
    private String nature;
    @JsonProperty("format")
    private String format;
    @JsonProperty("persist")
    private Boolean persist;
    @JsonProperty("type")
    private String type;
    @JsonProperty("order")
    private Integer order;
}