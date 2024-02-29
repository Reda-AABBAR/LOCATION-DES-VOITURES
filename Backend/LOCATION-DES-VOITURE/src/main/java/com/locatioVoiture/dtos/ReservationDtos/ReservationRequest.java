package com.locatioVoiture.dtos.ReservationDtos;

import java.util.Date;

import com.locatioVoiture.dtos.AdministrateurDtos.AdministrateurRequest;
import com.locatioVoiture.dtos.ClientDtos.ClientRequest;
import com.locatioVoiture.dtos.LivreurDtos.LivreurRequest;
import com.locatioVoiture.dtos.vehiculeDtos.VehiculeRequest;

import lombok.Builder;

@Builder
public record ReservationRequest(
		Date dateDebut,
		Date dateFin,
		boolean estContratSegnie,
		boolean estPaye,
		boolean estConfirmet,
		boolean estLivrer,
		ClientRequest clientRequest,
		LivreurRequest livreurRequest,
		VehiculeRequest vehiculeRequest,
		AdministrateurRequest administrateurRequest
		) {

}
