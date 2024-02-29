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

import com.locatioVoiture.dtos.ReservationDtos.ReservationRequest;
import com.locatioVoiture.dtos.ReservationDtos.ReservationResponse;
import com.locatioVoiture.entities.Reservation;
import com.locatioVoiture.mappers.reservationMapper.ReservationMapper;
import com.locatioVoiture.services.ReservationService;

@RestController
@RequestMapping("/reservations")
@CrossOrigin("*")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> addReservation(@RequestBody ReservationRequest reservationRequest) {
        Reservation newReservation = reservationService.addReservation(reservationRequest);
        ReservationResponse reservationResponse = ReservationMapper.toReservationResponse(newReservation);
        return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        ReservationResponse reservationResponse = ReservationMapper.toReservationResponse(reservation);
        return reservation != null ? new ResponseEntity<>(reservationResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Collection<ReservationResponse>> getAllReservations() {
        Collection<Reservation> reservations = reservationService.getAllReservations();
        Collection<ReservationResponse> reservationResponses = ReservationMapper.toReservationResponses(reservations);
        return new ResponseEntity<>(reservationResponses, HttpStatus.OK);
    }
    
    @GetMapping("/NotConfermed")
    public ResponseEntity<Collection<ReservationResponse>> getAllConfermedReservations() {
        Collection<Reservation> reservations = reservationService.getNotConfermedReservation();
        Collection<ReservationResponse> reservationResponses = ReservationMapper.toReservationResponses(reservations);
        return new ResponseEntity<>(reservationResponses, HttpStatus.OK);
    }
    
    @GetMapping("/confermed/notDelivred")
    public ResponseEntity<Collection<ReservationResponse>> getAllConfermedReservationsNotDelivered() {
        Collection<Reservation> reservations = reservationService.getAllConfermedNotLivred();
        Collection<ReservationResponse> reservationResponses = ReservationMapper.toReservationResponses(reservations);
        return new ResponseEntity<>(reservationResponses, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponse> updateReservation(@PathVariable Long id,
                                                                @RequestBody ReservationRequest reservationRequest) {
        Reservation updatedReservation = reservationService.updateReservation(reservationRequest, id);
        ReservationResponse reservationResponse = ReservationMapper.toReservationResponse(updatedReservation);
        return updatedReservation != null ? new ResponseEntity<>(reservationResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/confermed/{id}")
    public ResponseEntity<ReservationResponse> confermerReservation(@PathVariable Long id) {
		Reservation reservation = reservationService.confirmerReservation(id);
		return reservation != null ?
				new ResponseEntity<>(ReservationMapper.toReservationResponse(reservation), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    
    @PutMapping("/livrer/{id}")
    public ResponseEntity<ReservationResponse> livrerReservation(@PathVariable Long id) {
		Reservation reservation = reservationService.livrerReservation(id);
		return reservation != null ?
				new ResponseEntity<>(ReservationMapper.toReservationResponse(reservation), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    
    @PutMapping("/payer/{id}")
    public ResponseEntity<ReservationResponse> payerReservation(@PathVariable Long id) {
		Reservation reservation = reservationService.payerReservation(id);
		return reservation != null ?
				new ResponseEntity<>(ReservationMapper.toReservationResponse(reservation), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        boolean deleted = reservationService.deleteReservation(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}

