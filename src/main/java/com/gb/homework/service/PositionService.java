package com.gb.homework.service;

import com.gb.homework.exception.exceptions.InvalidApiKeyException;
import com.gb.homework.exception.exceptions.WrongInputException;
import com.gb.homework.model.Position;
import com.gb.homework.model.api.ResponseItem;
import com.gb.homework.model.credentials.PositionCredentials;
import com.gb.homework.repository.KeyRepository;
import com.gb.homework.repository.PositionRepository;
import com.gb.homework.util.InputValidator;
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

    @Autowired
    InputValidator inputValidator;

    @Value("https://jobs.github.com/positions.json?")
    private String API_URL;

    @Value("description=")
    private String API_DESCRIPTION;

    @Value("location=")
    private String API_LOCATION;

    @Value("page=")
    private String API_PAGINATION;

    @Value("&")
    private String API_AMPERSAND;

    @Value("localhost:8080/positions/")
    private String urlTemplate;

    public String createPosition(PositionCredentials positionCredentials) throws WrongInputException, InvalidApiKeyException {

        validateApiKey(positionCredentials.getApiKey());
        validatePositionInputs(positionCredentials);

        Position newPosition = Position.builder()
                .id(UUID.randomUUID())
                .title(positionCredentials.getTitle())
                .location(positionCredentials.getLocation())
                .build();

        newPosition.setUrl(urlTemplate + newPosition.getId());

        positionRepository.save(newPosition);
        return newPosition.getUrl();
    }

    public Position getPosition(UUID id) {

        return positionRepository.findById(id).get();
    }

    public Set<Position> searchPositions(String title, String location, UUID apiKey) throws Error, InvalidApiKeyException {

        validateApiKey(apiKey);
        Set<Position> dbPositions = searchInDatabase(title, location);
        getAllApiPosition(title, location, 0, dbPositions);
        return dbPositions;
    }

    private Set<Position> searchInDatabase(String title, String location) {
        return positionRepository.getPositionByKeywordAndLocation(title, location);
    };

    private Set<Position> getAllApiPosition(String title, String location, Integer page, Set<Position> positions) {
        String queryString = createApiUrl(title, location, page);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseItem[]> positionResponseEntity = restTemplate.exchange(
                queryString,
                HttpMethod.GET,
                null,
                ResponseItem[].class
        );
        ResponseItem[] res = positionResponseEntity.getBody();
        Set<Position> newPositions = responseConverter.convertResponseBody(res);
        if (newPositions.size() > 50) {
            positions.addAll(newPositions);
            return getAllApiPosition(title, location, page + 1, positions);
        }
        positions.addAll(newPositions);
        return positions;
    };

    private String createApiUrl(String title, String location, Integer page) {
        return API_URL
                + API_DESCRIPTION
                + title
                + API_AMPERSAND
                + API_LOCATION
                + location
                + API_AMPERSAND
                + API_PAGINATION
                + page;
    }

    private void validateApiKey(UUID apiKey) throws InvalidApiKeyException {
        keyRepository
                .findById(apiKey)
                .orElseThrow(() -> new InvalidApiKeyException("Wrong api key!", new Throwable()));
    }

    private void validatePositionInputs(PositionCredentials positionCredentials) throws WrongInputException {
        inputValidator.inputLengthValidation(
                positionCredentials.getTitle(),
                50,
                "Position title is too long!"
        );

        inputValidator.inputLengthValidation(
                positionCredentials.getLocation(),
                50,
                "Position location is too long!"
        );
    }

}
