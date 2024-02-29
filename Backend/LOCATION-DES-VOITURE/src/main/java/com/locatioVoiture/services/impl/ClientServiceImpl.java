package com.locatioVoiture.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.locatioVoiture.dtos.ClientDtos.ClientRequest;
import com.locatioVoiture.entities.Client;
import com.locatioVoiture.entities.Role;
import com.locatioVoiture.entities.Utilisateur;
import com.locatioVoiture.mappers.clientMapper.ClientMapper;
import com.locatioVoiture.repositories.ClientRepository;
import com.locatioVoiture.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Client addClient(ClientRequest clientRequest) {
		Client client = ClientMapper.toClient(clientRequest);
		if(client != null) {
			client.setPassword(new BCryptPasswordEncoder().encode(clientRequest.password()));
			client.setRoles(new ArrayList<Role>());
			client.getRoles().add(Role.CLIENT);
		}
		return clientRepository.save(client);
	}

	@Override
	public Client getClientById(Long id) {
		return clientRepository.findById(id).orElse(null);
	}

	@Override
	public Collection<Client> getAllClients() {
		return clientRepository.findAll();
	}

	@Override
	public Client updateClient(ClientRequest clientRequest, Long id) {
		Client client = getClientById(id);
		if(client != null) {
			client.setAdresse(clientRequest.adresse());
			client.setCIN(clientRequest.cIN());
			client.setDateNaissance(clientRequest.dateNaissance());
			client.setEmail(clientRequest.email());
			client.setNom(clientRequest.nom());
			client.setNumTele(clientRequest.numTele());
			client.setPassword(clientRequest.password());
			client.setPrenom(clientRequest.prenom());
			return clientRepository.save(client);
		}
		return null;
	}

	@Override
	public void deleteClient(Long id) {
		clientRepository.deleteById(id);
	}

	@Override
	public Client searchClientsByCIN(String cIN) {
		return clientRepository.findByCIN(cIN);
	}

	@Override
	public Client searchClientsByEmail(String email) {
		return clientRepository.findByEmail(email);
	}

}
