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

import com.locatioVoiture.dtos.CompanieDtos.CompanieRequest;
import com.locatioVoiture.dtos.CompanieDtos.CompanieResponse;
import com.locatioVoiture.entities.Companie;
import com.locatioVoiture.mappers.companieMapper.CompanieMapper;
import com.locatioVoiture.services.CompanieService;

@RestController
@RequestMapping("/companies")
@CrossOrigin("*")
public class CompanieController {

    @Autowired
    private CompanieService companieService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<CompanieResponse> addCompanie(@RequestBody CompanieRequest companieRequest) {
        Companie newCompanie = companieService.addCompanie(companieRequest);
        CompanieResponse companieResponse = CompanieMapper.toCompanieResponse(newCompanie);
        return new ResponseEntity<>(companieResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanieResponse> getCompanieById(@PathVariable Long id) {
        Companie companie = companieService.getCompanieById(id);
        CompanieResponse companieResponse = CompanieMapper.toCompanieResponse(companie);
        return companie != null ? new ResponseEntity<>(companieResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Collection<CompanieResponse>> getAllCompanies() {
        Collection<Companie> companies = companieService.getAllCompanies();
        Collection<CompanieResponse> companieResponses = CompanieMapper.toCompanieResponses(companies);
        return new ResponseEntity<>(companieResponses, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CompanieResponse> getCompanieByName(@PathVariable String name) {
        Companie companie = companieService.searchCompaniesByNom(name);
        CompanieResponse companieResponse = CompanieMapper.toCompanieResponse(companie);
        return companie != null ? new ResponseEntity<>(companieResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<CompanieResponse> updateCompanie(@PathVariable Long id,
                                                           @RequestBody CompanieRequest companieRequest) {
        Companie updatedCompanie = companieService.updateCompanie(companieRequest, id);
        CompanieResponse companieResponse = CompanieMapper.toCompanieResponse(updatedCompanie);
        return updatedCompanie != null ? new ResponseEntity<>(companieResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> deleteCompanie(@PathVariable Long id) {
        companieService.deleteCompanie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

