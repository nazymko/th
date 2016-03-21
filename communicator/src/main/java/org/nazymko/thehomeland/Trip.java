package org.nazymko.thehomeland;

/**
 * Created by JacksonGenerator on 16.03.16.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.nazymko.thehomeland.parser.Dto;

@Builder
public class Trip implements Dto {
    @JsonProperty("image")
    private String image;
    @JsonProperty("ticketsTotal")
    private String ticketsTotal;
    @JsonProperty("dateStart")
    private String dateStart;
    @JsonProperty("departureCityId")
    private String departureCityId;
    @JsonProperty("price")
    private String price;
    @JsonProperty("targetCityId")
    private String targetCityId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("typeId")
    private String typeId;
    @JsonProperty("title")
    private String title;
}