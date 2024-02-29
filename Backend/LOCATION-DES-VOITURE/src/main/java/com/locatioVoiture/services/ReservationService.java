package com.locatioVoiture.services;

import java.util.Collection;

import com.locatioVoiture.dtos.ReservationDtos.ReservationRequest;
import com.locatioVoiture.entities.Reservation;

public interface ReservationService {
    Reservation addReservation(ReservationRequest reservationRequest);
    Reservation getReservationById(Long id);
    Collection<Reservation> getAllReservations();
    Reservation updateReservation(ReservationRequest reservationRequest, Long id);
    boolean deleteReservation(Long id);
	Reservation confirmerReservation(Long id);
//	Collection<Reservation> getConfermedReservation();
	Reservation livrerReservation(Long id);
	Reservation payerReservation(Long id);
	Collection<Reservation> getAllConfermedNotLivred();
	Collection<Reservation> getNotConfermedReservation();
}
