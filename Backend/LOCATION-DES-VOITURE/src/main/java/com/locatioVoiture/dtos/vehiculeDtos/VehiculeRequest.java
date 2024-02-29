package com.locatioVoiture.dtos.vehiculeDtos;

import java.util.Collection;
import java.util.Date;

import com.locatioVoiture.dtos.CaractiristiqueDtos.CaractiristiqueRequest;
import com.locatioVoiture.dtos.CaractiristiqueDtos.CaractiristiqueResponse;
import com.locatioVoiture.dtos.CompanieDtos.CompanieRequest;
import com.locatioVoiture.dtos.agenceDtos.AgenceRequest;

import lombok.Builder;

@Builder
public record VehiculeRequest(
		 String matricule,
		 String modele,
		 Double prixParJour,
		 Double kilometrage,
		 Date modeleDate,
		 Collection<byte[]> photos,
		 Collection<String> contrats,
		 Collection<CaractiristiqueRequest> caractiristiqueRequests,
		 CompanieRequest companieRequest,
		 AgenceRequest agenceRequest
		) {

}
