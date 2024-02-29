package com.locatioVoiture.services;

import java.util.Collection;

import com.locatioVoiture.dtos.serviceClientDtos.ServiceClientRequest;
import com.locatioVoiture.entities.ServiceClient;
import com.locatioVoiture.entities.Utilisateur;

public interface ServiceClientService {
    ServiceClient addServiceClient(ServiceClientRequest serviceClientRequest);
    ServiceClient getServiceClientById(Long id);
    Collection<ServiceClient> getAllServiceClients();
    ServiceClient updateServiceClient(ServiceClientRequest serviceClientRequest, Long id);
    void deleteServiceClient(Long id);
	ServiceClient getServiceClientByCIN(String cIN);
	ServiceClient searchByEmail(String email);
}
