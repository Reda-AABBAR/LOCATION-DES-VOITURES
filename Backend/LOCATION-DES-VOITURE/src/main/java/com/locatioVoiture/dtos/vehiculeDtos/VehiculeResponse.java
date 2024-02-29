package com.locatioVoiture.dtos.vehiculeDtos;

import java.util.Collection;
import java.util.Date;

import com.locatioVoiture.dtos.CaractiristiqueDtos.CaractiristiqueResponse;
import com.locatioVoiture.dtos.CompanieDtos.CompanieResponse;
import com.locatioVoiture.dtos.ReservationDtos.ReservationResponse;

import lombok.Builder;

@Builder
public record VehiculeResponse(
		Long id,
		 String matricule,
		 String modele,
		 Double prixParJour,
		 Double kilometrage,
		 Date modeleDate,
		 Collection<byte[]> photos,
		 Collection<String> contrats,
		 Collection<ReservationResponse> reservationResponses,
		 Collection<CaractiristiqueResponse> caractiristiqueResponses,
		 CompanieResponse companieResponse
		) {

}
