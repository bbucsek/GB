package com.gb.homework.service;

import com.gb.homework.exception.exceptions.WrongInputException;
import com.gb.homework.model.Client;
import com.gb.homework.model.Key;
import com.gb.homework.repository.ClientRepository;
import com.gb.homework.repository.KeyRepository;
import com.gb.homework.util.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    KeyRepository keyRepository;

    @Autowired
    InputValidator inputValidator;

    public UUID createClient(Client client) throws WrongInputException {

        validateInputs(client);

        Client newClient = Client.builder()
                .name(client.getName())
                .email(client.getEmail())
                .build();

        UUID apiKey = UUID.randomUUID();

        Key newKey = Key.builder()
                .apiKey(apiKey)
                .client(newClient)
                .build();

        clientRepository.save(newClient);
        keyRepository.save(newKey);

        return apiKey;
    };

    private void validateInputs(Client client) throws WrongInputException {

        validateName(client.getName(), 100);
        inputValidator.emailValidator(client.getEmail());
    }

    private void validateName(String name, Integer maxLength) throws WrongInputException {
        inputValidator.inputLengthValidation(
                name,
                maxLength,
                "Name is too long!"
        );
    }

}
