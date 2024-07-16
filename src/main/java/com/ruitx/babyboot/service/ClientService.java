package com.ruitx.babyboot.service;

import com.ruitx.babyboot.dto.ClientDto;
import com.ruitx.babyboot.mapper.ClientMapper;
import com.ruitx.babyboot.model.Client;
import com.ruitx.babyboot.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDto> getAll() {
        List<ClientDto> clientDtos = new ArrayList<>();

        this.clientRepository.findAll()
                .forEach(e -> clientDtos.add(ClientMapper.toDto(e)));
        return clientDtos;
    }

    public ClientDto create(ClientDto client) {
        Client newClient = ClientMapper.fromDto(client);
        this.clientRepository.save(newClient);
        return ClientMapper.toDto(newClient);
    }

    public ClientDto update(long id, ClientDto client) {

        Client clientToUpdate;
        clientToUpdate = this.clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Client not found")
        );

        // TODO: Is this the right way to update?
        clientToUpdate.setFirstName(client.firstName());
        clientToUpdate.setLastName(client.lastName());
        this.clientRepository.save(clientToUpdate);

        // TODO: Assuming that everything is ok with the update
        return ClientMapper.toDto(clientToUpdate);
    }

    public ClientDto delete(long id) {
        Client clientToDelete = this.clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Client not found")
        );

        this.clientRepository.delete(clientToDelete);
        return ClientMapper.toDto(clientToDelete);
    }
}
