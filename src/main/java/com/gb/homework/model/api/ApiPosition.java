package com.gb.homework.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ApiPosition {

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("url")
    private String url;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("company")
    private String company;

    @JsonProperty("company_url")
    private String companyUrl;

    @JsonProperty("location")
    private String location;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("how_to_apply")
    private String howToApply;

    @JsonProperty("company_logo")
    private String companyLogo;
}
