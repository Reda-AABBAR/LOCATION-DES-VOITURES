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

import com.locatioVoiture.dtos.CaractiristiqueDtos.CaractiristiqueRequest;
import com.locatioVoiture.dtos.CaractiristiqueDtos.CaractiristiqueResponse;
import com.locatioVoiture.entities.Caractiristique;
import com.locatioVoiture.mappers.caractiristiqueMapper.CaractiristiqueMapper;
import com.locatioVoiture.services.CaractiristiqueService;

@RestController
@RequestMapping("/caractiristiques")
@CrossOrigin("*")
public class CaractiristiqueController {

    @Autowired
    private CaractiristiqueService caractiristiqueService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<CaractiristiqueResponse> addCaractiristique(@RequestBody CaractiristiqueRequest caractiristiqueRequest) {
        Caractiristique newCaractiristique = caractiristiqueService.addCaractiristique(caractiristiqueRequest);
        CaractiristiqueResponse caractiristiqueResponse = CaractiristiqueMapper.toCaractiristiqueResponse(newCaractiristique);
        return new ResponseEntity<>(caractiristiqueResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaractiristiqueResponse> getCaractiristiqueById(@PathVariable Long id) {
        Caractiristique caractiristique = caractiristiqueService.getCaractiristiqueById(id);
        CaractiristiqueResponse caractiristiqueResponse = CaractiristiqueMapper.toCaractiristiqueResponse(caractiristique);
        return caractiristique != null ? new ResponseEntity<>(caractiristiqueResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Collection<CaractiristiqueResponse>> getAllCaractiristiques() {
        Collection<Caractiristique> caractiristiques = caractiristiqueService.getAllCaractiristiques();
        Collection<CaractiristiqueResponse> caractiristiqueResponses = CaractiristiqueMapper.toCaractiristiqueResponses(caractiristiques);
        return new ResponseEntity<>(caractiristiqueResponses, HttpStatus.OK);
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<CaractiristiqueResponse> getCaractiristiqueByName(@PathVariable String name) {
        Caractiristique caractiristique = caractiristiqueService.searchCaractiristiquesByName(name);
        CaractiristiqueResponse caractiristiqueResponse = CaractiristiqueMapper.toCaractiristiqueResponse(caractiristique);
        return caractiristique != null ? new ResponseEntity<>(caractiristiqueResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<CaractiristiqueResponse> updateCaractiristique(@PathVariable Long id,
                                                                          @RequestBody CaractiristiqueRequest caractiristiqueRequest) {
        Caractiristique updatedCaractiristique = caractiristiqueService.updateCaractiristique(caractiristiqueRequest, id);
        CaractiristiqueResponse caractiristiqueResponse = CaractiristiqueMapper.toCaractiristiqueResponse(updatedCaractiristique);
        return updatedCaractiristique != null ? new ResponseEntity<>(caractiristiqueResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> deleteCaractiristique(@PathVariable Long id) {
        caractiristiqueService.deleteCaractiristique(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

