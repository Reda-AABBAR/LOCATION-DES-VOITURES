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

import com.locatioVoiture.dtos.LivreurDtos.LivreurRequest;
import com.locatioVoiture.dtos.LivreurDtos.LivreurResponse;
import com.locatioVoiture.entities.Livreur;
import com.locatioVoiture.mappers.livreurMapper.LivreurMapper;
import com.locatioVoiture.services.LivreurService;

@RestController
@RequestMapping("/livreurs")
@CrossOrigin("*")
public class LivreurController {

    @Autowired
    private LivreurService livreurService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<LivreurResponse> addLivreur(@RequestBody LivreurRequest livreurRequest) {
        Livreur newLivreur = livreurService.addLivreur(livreurRequest);
        LivreurResponse livreurResponse = LivreurMapper.toLivreurResponse(newLivreur);
        return new ResponseEntity<>(livreurResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivreurResponse> getLivreurById(@PathVariable Long id) {
        Livreur livreur = livreurService.getLivreurById(id);
        LivreurResponse livreurResponse = LivreurMapper.toLivreurResponse(livreur);
        return livreur != null ? new ResponseEntity<>(livreurResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Collection<LivreurResponse>> getAllLivreurs() {
        Collection<Livreur> livreurs = livreurService.getAllLivreurs();
        Collection<LivreurResponse> livreurResponses = LivreurMapper.toLivreurResponses(livreurs);
        return new ResponseEntity<>(livreurResponses, HttpStatus.OK);
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<LivreurResponse> getLivreurByEmail(@PathVariable("email") String email) {
    	Livreur livreur = livreurService.searchLivreursByEmail(email);
    	return livreur != null ?
    			new ResponseEntity<>(LivreurMapper.toLivreurResponse(livreur),HttpStatus.OK)
    			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/cin/{cin}")
    public ResponseEntity<LivreurResponse> getLivreurByCIN(@PathVariable String cin) {
        Livreur livreur = livreurService.searchLivreursByCIN(cin);
        LivreurResponse livreurResponse = LivreurMapper.toLivreurResponse(livreur);
        return livreur != null ? new ResponseEntity<>(livreurResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<LivreurResponse> updateLivreur(@PathVariable Long id,
                                                          @RequestBody LivreurRequest livreurRequest) {
        Livreur updatedLivreur = livreurService.updateLivreur(livreurRequest, id);
        LivreurResponse livreurResponse = LivreurMapper.toLivreurResponse(updatedLivreur);
        return updatedLivreur != null ? new ResponseEntity<>(livreurResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> deleteLivreur(@PathVariable Long id) {
        livreurService.deleteLivreur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

