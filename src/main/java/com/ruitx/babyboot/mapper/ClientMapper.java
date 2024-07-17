package com.ruitx.babyboot.mapper;

import com.ruitx.babyboot.dto.ClientDto;
import com.ruitx.babyboot.model.Client;

public class ClientMapper {
    public static ClientDto toDto(Client client) {
        return new ClientDto(client.getId(), client.getFirstName(), client.getLastName());
    }

    public static Client fromDto(ClientDto clientDto) {
        Client client = new Client();
        client.setId(clientDto.id());
        client.setFirstName(clientDto.firstName());
        client.setLastName(clientDto.lastName());
        return client;
    }
}
