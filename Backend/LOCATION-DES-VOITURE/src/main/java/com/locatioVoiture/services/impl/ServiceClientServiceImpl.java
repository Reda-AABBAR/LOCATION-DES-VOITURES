package com.locatioVoiture.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.locatioVoiture.dtos.serviceClientDtos.ServiceClientRequest;
import com.locatioVoiture.entities.Role;
import com.locatioVoiture.entities.ServiceClient;
import com.locatioVoiture.mappers.serviceClientMapper.ServiceClientMapper;
import com.locatioVoiture.services.AgenceService;
import com.locatioVoiture.services.ServiceClientService;
import com.locatioVoiture.repositories.serviceClientRepository;
@Service
public class ServiceClientServiceImpl implements ServiceClientService {

    @Autowired
    private serviceClientRepository serviceClientRepository;
    
    @Autowired
    private AgenceService agenceService;

    @Override
    public ServiceClient addServiceClient(ServiceClientRequest serviceClientRequest) {
        ServiceClient newServiceClient = ServiceClientMapper.toServiceClient(serviceClientRequest);
        newServiceClient.setRoles(new ArrayList<Role>());
        newServiceClient.setPassword(new BCryptPasswordEncoder().encode(serviceClientRequest.password()));
        newServiceClient.getRoles().add(Role.SERVICE_CLIENT);
        newServiceClient.setAgence(serviceClientRequest.agenceRequest() != null ?
        		agenceService.searchAgencesByNom(serviceClientRequest.agenceRequest().nom())
        		: null);
        return serviceClientRepository.save(newServiceClient);
    }

    @Override
    public ServiceClient getServiceClientById(Long id) {
        return serviceClientRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<ServiceClient> getAllServiceClients() {
        return serviceClientRepository.findAll();
    }

    @Override
    public ServiceClient updateServiceClient(ServiceClientRequest serviceClientRequest, Long id) {
        ServiceClient existingServiceClient = getServiceClientById(id);
        ServiceClient serviceClient = ServiceClientMapper.toServiceClient(serviceClientRequest);

        if (existingServiceClient != null) {
        	serviceClient.setId(id);
            serviceClient.setRoles(existingServiceClient.getRoles());
            serviceClient.setReponses(existingServiceClient.getReponses());
            serviceClient.setAgence(serviceClientRequest.agenceRequest() != null ?
            		agenceService.searchAgencesByNom(serviceClientRequest.agenceRequest().nom())
            		: null );
            return serviceClientRepository.save(serviceClient);
        }
        return null;
    }

    @Override
    public void deleteServiceClient(Long id) {
        serviceClientRepository.deleteById(id);
    }

    @Override
    public ServiceClient getServiceClientByCIN(String cIN) {
        return serviceClientRepository.findByCIN(cIN);
    }

	@Override
	public ServiceClient searchByEmail(String email) {
		return serviceClientRepository.findByEmail(email);
	}
}

