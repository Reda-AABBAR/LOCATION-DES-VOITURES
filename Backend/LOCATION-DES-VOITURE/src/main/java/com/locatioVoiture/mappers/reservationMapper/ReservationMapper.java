package com.locatioVoiture.mappers.reservationMapper;

import java.util.Collection;
import java.util.stream.Collectors;

import com.locatioVoiture.dtos.ReservationDtos.ReservationRequest;
import com.locatioVoiture.dtos.ReservationDtos.ReservationResponse;
import com.locatioVoiture.entities.Reservation;
import com.locatioVoiture.mappers.administrateurMapper.AdministrateurMapper;
import com.locatioVoiture.mappers.clientMapper.ClientMapper;
import com.locatioVoiture.mappers.livreurMapper.LivreurMapper;
import com.locatioVoiture.mappers.vehiculeMapper.VehiculeMapper;

public class ReservationMapper {
	
	public static Reservation toReservation(ReservationRequest reservationRequest) {
		Reservation reservation = null;
		
		if(reservationRequest != null) {
			reservation = Reservation.builder()
					.administrateur(AdministrateurMapper.toAdministrateur(reservationRequest.administrateurRequest()))
					.client(ClientMapper.toClient(reservationRequest.clientRequest()))
					.dateDepart(reservationRequest.dateDebut())
					.dateFin(reservationRequest.dateFin())
					.estConfirmet(reservationRequest.estConfirmet())
					.estContratSegnie(reservationRequest.estContratSegnie())
					.estLivrer(reservationRequest.estLivrer())
					.estPaye(reservationRequest.estPaye())
					.livreur(LivreurMapper.toLivreur(reservationRequest.livreurRequest()))
					.vehicule(VehiculeMapper.toVehicule(reservationRequest.vehiculeRequest()))
					.build();
		}
		return reservation;
	}

	public static ReservationResponse toReservationResponse(Reservation reservation) {
		ReservationResponse reservationResponse = null;
		if(reservation != null) {
			reservationResponse = ReservationResponse.builder()
					.administrateurResponse(AdministrateurMapper.toAdministrateurResponsefilter(reservation.getAdministrateur()))
					.clientResponse(ClientMapper.toClientResponseFilter(reservation.getClient()))
					.dateDebut(reservation.getDateDepart())
					.dateFin(reservation.getDateFin())
					.dateReservation(reservation.getDateReservation())
					.estConfirmet(reservation.isEstConfirmet())
					.estContratSegnie(reservation.isEstContratSegnie())
					.estLivrer(reservation.isEstLivrer())
					.estPaye(reservation.isEstPaye())
					.id(reservation.getId())
					.livreurResponse(LivreurMapper.toLivreurResponseFilter(reservation.getLivreur()))
					.vehiculeResponce(VehiculeMapper.toVehiculeResponseFilter(reservation.getVehicule()))
					.build();
		}
		return reservationResponse;
	}

	public static Collection<ReservationResponse> toReservationResponses(Collection<Reservation> reservations) {
		return reservations != null ?
				reservations.stream()
				.map(r->toReservationResponse(r))
				.collect(Collectors.toList())
				: null;
	}

}
