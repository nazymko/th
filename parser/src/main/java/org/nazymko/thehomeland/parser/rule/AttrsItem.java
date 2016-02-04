package org.nazymko.thehomeland.parser.rule;

/**
 * Created by JacksonGenerator on 02.12.15.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
public class AttrsItem implements Serializable {
    @NonNull
    @JsonProperty("path")
    private String path;
    @NonNull
    @JsonProperty("attr")
    private String attr;
    @NonNull
    @JsonProperty("type")
    private String type;


}