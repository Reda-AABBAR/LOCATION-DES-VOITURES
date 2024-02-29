package com.locatioVoiture.mappers.serviceClientMapper;

import java.util.Collection;
import java.util.stream.Collectors;

import com.locatioVoiture.dtos.serviceClientDtos.ServiceClientRequest;
import com.locatioVoiture.dtos.serviceClientDtos.ServiceClientResponse;
import com.locatioVoiture.entities.ServiceClient;
import com.locatioVoiture.mappers.reponderMapper.ReponderMapper;

public class ServiceClientMapper {

	public static ServiceClient toServiceClient(ServiceClientRequest serviceClientRequest) {
		ServiceClient serviceClient = null;
		if(serviceClientRequest != null) {
			serviceClient = new ServiceClient();
			serviceClient.setAdresse(serviceClientRequest.adresse());
			serviceClient.setCIN(serviceClientRequest.cIN());
			serviceClient.setDateNaissance(serviceClientRequest.dateNaissance());
			serviceClient.setEmail(serviceClientRequest.email());
			serviceClient.setNom(serviceClientRequest.nom());
			serviceClient.setNumTele(serviceClientRequest.numTele());
			serviceClient.setPassword(serviceClientRequest.password());
			serviceClient.setPrenom(serviceClientRequest.prenom());
		}
		return serviceClient;
	}
	
	public static ServiceClientResponse toServiceClientResponse(ServiceClient serviceClient) {
		ServiceClientResponse serviceClientResponse = null;
		
		if(serviceClient != null) {
			serviceClientResponse = ServiceClientResponse.builder()
					.adresse(serviceClient.getAdresse())
					.cIN(serviceClient.getCIN())
					.dateNaissance(serviceClient.getDateNaissance())
					.email(serviceClient.getEmail())
					.id(serviceClient.getId())
					.nom(serviceClient.getNom())
					.numTele(serviceClient.getNumTele())
					.password(serviceClient.getPassword())
					.prenom(serviceClient.getPrenom())
					.reponderResponses(ReponderMapper.toReponderResponses(serviceClient.getReponses()))
					.roles(serviceClient.getRoles())
					.build();
		}
		
		return serviceClientResponse;
	}

	public static Collection<ServiceClientResponse> toServiceClientResponses(Collection<ServiceClient> serviceClients) {
		return serviceClients != null ?
				serviceClients.stream().map(s->toServiceClientResponse(s))
				.collect(Collectors.toList())
				: null;
	}

}
