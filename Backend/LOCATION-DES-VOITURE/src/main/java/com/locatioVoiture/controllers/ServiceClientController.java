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

import com.locatioVoiture.dtos.serviceClientDtos.ServiceClientRequest;
import com.locatioVoiture.dtos.serviceClientDtos.ServiceClientResponse;
import com.locatioVoiture.entities.ServiceClient;
import com.locatioVoiture.mappers.serviceClientMapper.ServiceClientMapper;
import com.locatioVoiture.services.ServiceClientService;

@RestController
@RequestMapping("/service-clients")
@CrossOrigin("*")
public class ServiceClientController {

    @Autowired
    private ServiceClientService serviceClientService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ServiceClientResponse> addServiceClient(@RequestBody ServiceClientRequest serviceClientRequest) {
        ServiceClient newServiceClient = serviceClientService.addServiceClient(serviceClientRequest);
        ServiceClientResponse serviceClientResponse = ServiceClientMapper.toServiceClientResponse(newServiceClient);
        return new ResponseEntity<>(serviceClientResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceClientResponse> getServiceClientById(@PathVariable Long id) {
        ServiceClient serviceClient = serviceClientService.getServiceClientById(id);
        ServiceClientResponse serviceClientResponse = ServiceClientMapper.toServiceClientResponse(serviceClient);
        return serviceClient != null ? new ResponseEntity<>(serviceClientResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Collection<ServiceClientResponse>> getAllServiceClients() {
        Collection<ServiceClient> serviceClients = serviceClientService.getAllServiceClients();
        Collection<ServiceClientResponse> serviceClientResponses = ServiceClientMapper.toServiceClientResponses(serviceClients);
        return new ResponseEntity<>(serviceClientResponses, HttpStatus.OK);
    }

    @GetMapping("/cin/{cin}")
    public ResponseEntity<ServiceClientResponse> getServiceClientByCIN(@PathVariable String cin) {
        ServiceClient serviceClient = serviceClientService.getServiceClientByCIN(cin);
        ServiceClientResponse serviceClientResponse = ServiceClientMapper.toServiceClientResponse(serviceClient);
        return serviceClient != null ? new ResponseEntity<>(serviceClientResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<ServiceClientResponse> getLivreurByEmail(@PathVariable("email") String email) {
    	ServiceClient serviceClient = serviceClientService.searchByEmail(email);
    	return serviceClient != null ?
    			new ResponseEntity<>(ServiceClientMapper.toServiceClientResponse(serviceClient),HttpStatus.OK)
    			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ServiceClientResponse> updateServiceClient(@PathVariable Long id,
                                                                    @RequestBody ServiceClientRequest serviceClientRequest) {
        ServiceClient updatedServiceClient = serviceClientService.updateServiceClient(serviceClientRequest, id);
        ServiceClientResponse serviceClientResponse = ServiceClientMapper.toServiceClientResponse(updatedServiceClient);
        return updatedServiceClient != null ? new ResponseEntity<>(serviceClientResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> deleteServiceClient(@PathVariable Long id) {
        serviceClientService.deleteServiceClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

