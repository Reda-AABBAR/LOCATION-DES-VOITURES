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

import com.locatioVoiture.dtos.agenceDtos.AgenceRequest;
import com.locatioVoiture.dtos.agenceDtos.AgenceResponse;
import com.locatioVoiture.entities.Agence;
import com.locatioVoiture.mappers.agenceMapper.AgenceMapper;
import com.locatioVoiture.services.AgenceService;

@RestController
@RequestMapping("/agences")
@CrossOrigin("*")
public class AgenceController {

    @Autowired
    private AgenceService agenceService;

    @PostMapping
    public ResponseEntity<AgenceResponse> addAgence(@RequestBody AgenceRequest agenceRequest) {
        Agence newAgence = agenceService.addAgence(agenceRequest);
        AgenceResponse agenceResponse = AgenceMapper.toAgenceResponse(newAgence);
        return new ResponseEntity<>(agenceResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenceResponse> getAgenceById(@PathVariable Long id) {
        Agence agence = agenceService.getAgenceById(id);
        AgenceResponse agenceResponse = AgenceMapper.toAgenceResponse(agence);
        return agence != null ? new ResponseEntity<>(agenceResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Collection<AgenceResponse>> getAllAgences() {
        Collection<Agence> agences = agenceService.getAllAgences();
        Collection<AgenceResponse> agenceResponses = AgenceMapper.toAgenceResponses(agences);
        return new ResponseEntity<>(agenceResponses, HttpStatus.OK);
    }
    
    @GetMapping("/ville/{ville}")
    public ResponseEntity<Collection<AgenceResponse>> getAgencesByVille(@PathVariable String ville) {
        Collection<Agence> agences = agenceService.searchAgencesByVille(ville);
        Collection<AgenceResponse> agenceResponses = AgenceMapper.toAgenceResponses(agences);
        return new ResponseEntity<>(agenceResponses, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<AgenceResponse> getAgenceByName(@PathVariable String name) {
        Agence agence = agenceService.searchAgencesByNom(name);
        AgenceResponse agenceResponse = AgenceMapper.toAgenceResponse(agence);
        return agence != null ? new ResponseEntity<>(agenceResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<AgenceResponse> updateAgence(@PathVariable Long id,
                                                              @RequestBody AgenceRequest agenceRequest) {
        Agence updatedAgence = agenceService.updateAgence(agenceRequest, id);
        AgenceResponse agenceResponse = AgenceMapper.toAgenceResponse(updatedAgence);
        return updatedAgence != null ? new ResponseEntity<>(agenceResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> deleteAgence(@PathVariable Long id) {
        agenceService.deleteAgence(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

