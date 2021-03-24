package com.gb.homework.service;

import com.gb.homework.model.Client;
import com.gb.homework.model.Key;
import com.gb.homework.repository.ClientRepository;
import com.gb.homework.repository.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    KeyRepository keyRepository;

    public UUID createClient(Client client) {
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
}
