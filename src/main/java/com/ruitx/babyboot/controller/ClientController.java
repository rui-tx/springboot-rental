package com.ruitx.babyboot.controller;

import com.ruitx.babyboot.dto.ClientDto;
import com.ruitx.babyboot.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<List<ClientDto>> getAll() {
        List<ClientDto> clients = this.clientService.getAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping(path = {"", "/"})
    public ResponseEntity<ClientDto> create(@Valid @RequestBody ClientDto client) {
        ClientDto response = this.clientService.create(client);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ClientDto> update(@Valid @PathVariable long id, @Valid @RequestBody ClientDto client) {
        ClientDto response = this.clientService.update(id, client);
        //return response == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(response, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ClientDto> delete(@PathVariable long id) {
        ClientDto response = this.clientService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
