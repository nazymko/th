package org.nazymko.thehomeland.parser.rule;

/**
 * Created by JacksonGenerator on 15.03.16.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Meta {
    @JsonProperty("languages")
    private List<String> languages;
    @JsonProperty("topics")
    private List<String> topics;
    @JsonProperty("type")
    private List<String> type;
}