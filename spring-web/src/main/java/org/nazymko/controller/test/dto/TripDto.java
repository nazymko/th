package org.nazymko.controller.test.dto;

/**
 * Created by JacksonGenerator on 26.05.16.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TripDto {
    @JsonProperty("image")
    private String image;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("targetCity")
    private String targetCity;
    @JsonProperty("departureCity")
    private String departureCity;
    @JsonProperty("departureAddress")
    private String departureAddress;
    @JsonProperty("description")
    private String description;
    @JsonProperty("phones")
    private List<String> phones;
    @JsonProperty("dateEnd")
    private String dateEnd;
    @JsonProperty("title")
    private String title;
    @JsonProperty("type")
    private String type;
    @JsonProperty("url")
    private String url;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("dateStart")
    private String dateStart;
    @JsonProperty("ticketsTotal")
    private String ticketsTotal;
    @JsonProperty("price")
    private String price;
}