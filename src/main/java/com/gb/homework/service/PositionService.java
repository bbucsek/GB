package com.gb.homework.service;

import com.gb.homework.model.Position;
import com.gb.homework.model.api.ResponseItem;
import com.gb.homework.model.credentials.PositionCredentials;
import com.gb.homework.repository.KeyRepository;
import com.gb.homework.repository.PositionRepository;
import com.gb.homework.util.ResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.UUID;

@Component
public class PositionService {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    KeyRepository keyRepository;

    @Autowired
    ResponseConverter responseConverter;

    @Value("https://jobs.github.com/positions.json?")
    String API_URL;

    @Value("description=")
    String API_DESCRIPTION;

    @Value("location=")
    String API_LOCATION;

    @Value("page=")
    String API_PAGINATION;

    @Value("&")
    String API_AMPERSAND;

    public String createPosition(PositionCredentials positionCredentials) throws Error{

        keyRepository
                .findById(positionCredentials.getApiKey())
                .orElseThrow(() -> new Error("not good"));

        Position newPosition = Position.builder()
                .title(positionCredentials.getTitle())
                .location(positionCredentials.getLocation())
                .build();
        newPosition.setUrl("localhost:8080/positions/" + newPosition.getId());

        positionRepository.save(newPosition);
        return newPosition.getUrl();
    }

    public Position getPosition(UUID id) {

        return positionRepository.findById(id).get();
    }

    public Set<Position> searchPositions(String title, String location, UUID apiKey) throws Error {

        keyRepository
                .findById(apiKey)
                .orElseThrow(() -> new Error("not good"));
        searchInApi(title, location);

        ResponseItem[] pos = searchInApi(title, location);
        Set<Position> apiPositions = responseConverter.convertResponseBody(pos);
        Set<Position> dbPositions = searchInDatabase(title, location);
        apiPositions.addAll(dbPositions);
        return apiPositions;
    }

    private Set<Position> searchInDatabase(String title, String location) {
        return positionRepository.getPositionByKeywordAndLocation(title, location);
    };

    private ResponseItem[] searchInApi(String title, String location) {
        String queryString = createApiUrl(title, location);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseItem[]> positionResponseEntity = restTemplate.exchange(queryString, HttpMethod.GET, null, ResponseItem[].class);
        return positionResponseEntity.getBody();
    }

    private String createApiUrl(String title, String location) {
        return API_URL + API_DESCRIPTION + title + API_AMPERSAND + API_LOCATION + location;
    }
}
