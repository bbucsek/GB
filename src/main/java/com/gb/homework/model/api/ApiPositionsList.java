package com.gb.homework.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiPositionsList {

    @JsonProperty
    private List<ApiPosition> positions;
}
