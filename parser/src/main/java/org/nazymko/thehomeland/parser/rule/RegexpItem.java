package org.nazymko.thehomeland.parser.rule;

/**
 * Created by JacksonGenerator on 15.03.16.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RegexpItem {
    @JsonProperty("expression")
    private String expression;
    @JsonProperty("type")
    private String type;
    @JsonProperty("group")
    private List<GroupItem> group;
}