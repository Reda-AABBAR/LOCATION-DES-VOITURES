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

import com.locatioVoiture.dtos.AdministrateurDtos.AdministrateurRequest;
import com.locatioVoiture.dtos.AdministrateurDtos.AdministrateurResponse;
import com.locatioVoiture.entities.Administrateur;
import com.locatioVoiture.mappers.administrateurMapper.AdministrateurMapper;
import com.locatioVoiture.services.AdministrateurService;

@RestController
@RequestMapping("/administrateurs")
@CrossOrigin("*")
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<AdministrateurResponse> addAdministrateur(@RequestBody AdministrateurRequest administrateurRequest) {
        Administrateur newAdministrateur = administrateurService.addAministrateur(administrateurRequest);
        AdministrateurResponse administrateurResponse = AdministrateurMapper.toAdministrateurResponse(newAdministrateur);
        return new ResponseEntity<>(administrateurResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministrateurResponse> getAdministrateurById(@PathVariable Long id) {
        Administrateur administrateur = administrateurService.getAdministrateurById(id);
        AdministrateurResponse administrateurResponse = AdministrateurMapper.toAdministrateurResponse(administrateur);
        return administrateur != null ? new ResponseEntity<>(administrateurResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Collection<AdministrateurResponse>> getAllAdministrateurs() {
        Collection<Administrateur> administrateurs = administrateurService.getAllAdministrateurs();
        Collection<AdministrateurResponse> administrateurResponses = AdministrateurMapper.toAdministrateurResponses(administrateurs);
        return new ResponseEntity<>(administrateurResponses, HttpStatus.OK);
    }
    
    @GetMapping("/cin/{cin}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<AdministrateurResponse> getAdministrateurByCIN(@PathVariable String cin) {
        Administrateur administrateur = administrateurService.searchAdministrateursByCIN(cin);
        AdministrateurResponse administrateurResponse = AdministrateurMapper.toAdministrateurResponse(administrateur);
        return administrateur != null ? new ResponseEntity<>(administrateurResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<AdministrateurResponse> getAdminByEmail(@PathVariable("email") String email) {
    	Administrateur administrateur = administrateurService.findByEmail(email);
    	return administrateur != null ?
    			new ResponseEntity<AdministrateurResponse>(AdministrateurMapper.toAdministrateurResponse(administrateur),HttpStatus.OK)
    			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<AdministrateurResponse> updateAdministrateur(@PathVariable Long id,
                                                              @RequestBody AdministrateurRequest administrateurRequest) {
        Administrateur updatedAdministrateur = administrateurService.updateAdministrateur(administrateurRequest, id);
        AdministrateurResponse administrateurResponse = AdministrateurMapper.toAdministrateurResponse(updatedAdministrateur);
        return updatedAdministrateur != null ? new ResponseEntity<>(administrateurResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable Long id) {
        administrateurService.deleteAdministrateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
