package com.locatioVoiture.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locatioVoiture.dtos.ReponserDtos.ReponderRequest;
import com.locatioVoiture.dtos.ReponserDtos.ReponderResponse;
import com.locatioVoiture.entities.Reponder;
import com.locatioVoiture.mappers.reponderMapper.ReponderMapper;
import com.locatioVoiture.services.ReponderService;

@RestController
@RequestMapping("/reponders")
@CrossOrigin("*")
public class ReponderController {

    @Autowired
    private ReponderService reponderService;

    @PostMapping
    public ResponseEntity<ReponderResponse> addReponder(@RequestBody ReponderRequest reponderRequest) {
        Reponder newReponder = reponderService.addReponder(reponderRequest);
        ReponderResponse reponderResponse = ReponderMapper.toReponderResponse(newReponder);
        return new ResponseEntity<>(reponderResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReponderResponse> getReponderById(@PathVariable Long id) {
        Reponder reponder = reponderService.getReponderById(id);
        ReponderResponse reponderResponse = ReponderMapper.toReponderResponse(reponder);
        return reponder != null ? new ResponseEntity<>(reponderResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Collection<ReponderResponse>> getAllReponders() {
        Collection<Reponder> reponders = reponderService.getAllReponders();
        Collection<ReponderResponse> reponderResponses = ReponderMapper.toReponderResponses(reponders);
        return new ResponseEntity<>(reponderResponses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReponderResponse> updateReponder(@PathVariable Long id,
                                                          @RequestBody ReponderRequest reponderRequest) {
        Reponder updatedReponder = reponderService.updateReponder(reponderRequest, id);
        ReponderResponse reponderResponse = ReponderMapper.toReponderResponse(updatedReponder);
        return updatedReponder != null ? new ResponseEntity<>(reponderResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReponder(@PathVariable Long id) {
        reponderService.deleteReponder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

