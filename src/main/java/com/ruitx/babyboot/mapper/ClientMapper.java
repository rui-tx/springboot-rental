package com.ruitx.babyboot.mapper;

import com.ruitx.babyboot.dto.ClientDto;
import com.ruitx.babyboot.model.Client;

public class ClientMapper {
    public static ClientDto toDto(Client client) {
        return new ClientDto(client.getFirstName(), client.getLastName());
    }

    public static Client fromDto(ClientDto clientDto) {
        Client client = new Client();
        client.setFirstName(clientDto.firstName());
        client.setLastName(clientDto.lastName());
        return client;
    }
}
