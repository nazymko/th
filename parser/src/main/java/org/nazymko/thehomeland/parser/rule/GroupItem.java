package org.nazymko.thehomeland.parser.rule;

/**
 * Created by JacksonGenerator on 03.03.16.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GroupItem {
    @JsonProperty("type")
    private String type;
    @JsonProperty("order")
    private Integer order;
}