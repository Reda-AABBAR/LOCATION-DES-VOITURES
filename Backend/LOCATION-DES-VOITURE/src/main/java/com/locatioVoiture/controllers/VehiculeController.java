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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.locatioVoiture.dtos.vehiculeDtos.VehiculeRequest;
import com.locatioVoiture.dtos.vehiculeDtos.VehiculeResponse;
import com.locatioVoiture.entities.Vehicule;
import com.locatioVoiture.mappers.vehiculeMapper.VehiculeMapper;
import com.locatioVoiture.services.VehiculeService;

@RestController
@RequestMapping("/vehicules")
@CrossOrigin("*")
public class VehiculeController {

    @Autowired
    private VehiculeService vehiculeService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<VehiculeResponse> addVehicule(@RequestBody VehiculeRequest vehiculeRequest) {
        Vehicule newVehicule = vehiculeService.addVehicule(vehiculeRequest);
        VehiculeResponse vehiculeResponse = VehiculeMapper.toVehiculeResponse(newVehicule);
        return new ResponseEntity<>(vehiculeResponse, HttpStatus.CREATED);
    }
    
    @PostMapping("/images/{vehicleId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<String> addImageToVehicle(@PathVariable Long vehicleId, @RequestParam("file") MultipartFile file) {
        String result = vehiculeService.setImageToVehicule(vehicleId, file);

        if ("EMPTY".equals(result)) {
            return new ResponseEntity<>("File is empty", HttpStatus.CREATED);
        } else if ("NOT_FOUND".equals(result)) {
            return new ResponseEntity<>("Vehicle not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Image added successfully", HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculeResponse> getVehiculeById(@PathVariable Long id) {
        Vehicule vehicule = vehiculeService.getVehiculeById(id);
        VehiculeResponse vehiculeResponse = VehiculeMapper.toVehiculeResponse(vehicule);
        return vehicule != null ? new ResponseEntity<>(vehiculeResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Collection<VehiculeResponse>> getAllVehicules() {
        Collection<Vehicule> vehicules = vehiculeService.getAllVehicules();
        Collection<VehiculeResponse> vehiculeResponses = VehiculeMapper.toVehiculeResponses(vehicules);
        return new ResponseEntity<>(vehiculeResponses, HttpStatus.OK);
    }

    @GetMapping("/matricule/{matricule}")
    public ResponseEntity<VehiculeResponse> getVehiculeByMatricule(@PathVariable String matricule) {
        Vehicule vehicule = vehiculeService.searchVehiculesByMatricule(matricule);
        VehiculeResponse vehiculeResponse = VehiculeMapper.toVehiculeResponse(vehicule);
        return vehicule != null ? new ResponseEntity<>(vehiculeResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<VehiculeResponse> updateVehicule(@PathVariable Long id,
                                                           @RequestBody VehiculeRequest vehiculeRequest) {
        Vehicule updatedVehicule = vehiculeService.updateVehicule(vehiculeRequest, id);
        VehiculeResponse vehiculeResponse = VehiculeMapper.toVehiculeResponse(updatedVehicule);
        return updatedVehicule != null ? new ResponseEntity<>(vehiculeResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        vehiculeService.deleteVehicule(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

