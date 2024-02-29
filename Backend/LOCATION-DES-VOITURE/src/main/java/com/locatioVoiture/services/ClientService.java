package com.locatioVoiture.services;

import java.util.Collection;

import com.locatioVoiture.dtos.ClientDtos.ClientRequest;
import com.locatioVoiture.entities.Client;
import com.locatioVoiture.entities.Utilisateur;

public interface ClientService {
	    Client addClient(ClientRequest clientRequest);
	    Client getClientById(Long id);
	    Collection<Client> getAllClients();
	    Client updateClient(ClientRequest clientRequest, Long id);
	    void deleteClient(Long id);
	    Client searchClientsByCIN(String cIN);
		Client searchClientsByEmail(String email);
}
