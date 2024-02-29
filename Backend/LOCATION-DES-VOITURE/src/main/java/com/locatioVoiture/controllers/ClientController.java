package com.locatioVoiture.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locatioVoiture.dtos.ClientDtos.ClientRequest;
import com.locatioVoiture.dtos.ClientDtos.ClientResponse;
import com.locatioVoiture.entities.Client;
import com.locatioVoiture.mappers.clientMapper.ClientMapper;
import com.locatioVoiture.services.ClientService;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> addClient(@RequestBody ClientRequest clientRequest) {
        Client newClient = clientService.addClient(clientRequest);
        ClientResponse clientResponse = ClientMapper.toClientResponse(newClient);
        return new ResponseEntity<>(clientResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        ClientResponse clientResponse = ClientMapper.toClientResponse(client);
        return client != null ? new ResponseEntity<>(clientResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Collection<ClientResponse>> getAllClients() {
        Collection<Client> clients = clientService.getAllClients();
        Collection<ClientResponse> clientResponses = ClientMapper.toClientResponses(clients);
        return new ResponseEntity<>(clientResponses, HttpStatus.OK);
    }

    @GetMapping("/CIN/{cin}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ClientResponse> getClientByCIN(@PathVariable String cin) {
        Client client = clientService.searchClientsByCIN(cin);
        ClientResponse clientResponse = ClientMapper.toClientResponse(client);
        return client != null ? new ResponseEntity<>(clientResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<ClientResponse> getClientByEmail(@PathVariable("email") String email) {
    	Client client = clientService.searchClientsByEmail(email);
    	return client != null ?
    			new ResponseEntity<>(ClientMapper.toClientResponse(client),HttpStatus.OK)
    			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable Long id,
                                                      @RequestBody ClientRequest clientRequest) {
        Client updatedClient = clientService.updateClient(clientRequest, id);
        ClientResponse clientResponse = ClientMapper.toClientResponse(updatedClient);
        return updatedClient != null ? new ResponseEntity<>(clientResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

