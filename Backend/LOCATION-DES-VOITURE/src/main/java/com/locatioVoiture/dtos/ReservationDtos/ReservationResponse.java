package com.locatioVoiture.dtos.ReservationDtos;

import java.util.Date;

import com.locatioVoiture.dtos.AdministrateurDtos.AdministrateurResponse;
import com.locatioVoiture.dtos.ClientDtos.ClientResponse;
import com.locatioVoiture.dtos.LivreurDtos.LivreurResponse;
import com.locatioVoiture.dtos.vehiculeDtos.VehiculeResponse;

import lombok.Builder;
@Builder
public record ReservationResponse(
		Long id,
		Date dateDebut,
		Date dateFin,
		Date dateReservation,
		boolean estContratSegnie,
		boolean estPaye,
		boolean estConfirmet,
		boolean estLivrer,
		ClientResponse clientResponse,
		LivreurResponse livreurResponse,
		VehiculeResponse vehiculeResponce,
		AdministrateurResponse administrateurResponse
		) {

}
