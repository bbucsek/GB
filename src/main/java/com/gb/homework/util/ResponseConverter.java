package com.gb.homework.util;

import com.gb.homework.model.Position;
import com.gb.homework.model.api.ResponseItem;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ResponseConverter {

    public Set<Position> convertResponseBody(ResponseItem[] response) {
        Set<Position> positions = Arrays.stream(response).map(position -> {
            Position pos = Position.builder()
                    .title(position.getTitle())
                    .location(position.getLocation())
                    .url(position.getUrl())
                    .build();
            return pos;
        }).collect(Collectors.toSet());
        return positions;
    };
}
