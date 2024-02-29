package com.locatioVoiture.mappers.caractiristiqueMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import com.locatioVoiture.dtos.CaractiristiqueDtos.CaractiristiqueRequest;
import com.locatioVoiture.dtos.CaractiristiqueDtos.CaractiristiqueResponse;
import com.locatioVoiture.dtos.vehiculeDtos.VehiculeResponse;
import com.locatioVoiture.entities.Caractiristique;
import com.locatioVoiture.mappers.vehiculeMapper.VehiculeMapper;

public class CaractiristiqueMapper {
	public static Caractiristique toCaractiristique(CaractiristiqueRequest caractiristiqueRequest) {
		Caractiristique caractiristique = null;
		if(caractiristiqueRequest != null) {
			caractiristique = Caractiristique.builder()
					.nom(caractiristiqueRequest.nom())
					.build();
		}
		return caractiristique;
	}
	
	public static CaractiristiqueResponse toCaractiristiqueResponse(Caractiristique caractiristique) {
		CaractiristiqueResponse caractiristiqueResponse = null;
		Collection<VehiculeResponse> vehiculeResponces = new ArrayList<VehiculeResponse>();
		if(caractiristique != null) {
			
//			vehiculeResponces = caractiristique.getVehicules() != null?
//					caractiristique.getVehicules()
//					.stream()
//					.map(vehicule->VehiculeMapper.toVehiculeResponse(vehicule))
//					.collect(Collectors.toList())
//					: null;
			
			caractiristiqueResponse = CaractiristiqueResponse.builder()
					.id(caractiristique.getId())
					.nom(caractiristique.getNom())
//					.vehiculeResponces(vehiculeResponces)
					.build();
		}
		
		return caractiristiqueResponse;
	}

	public static Collection<Caractiristique> toCaractiristiques(
			Collection<CaractiristiqueRequest> caractiristiqueRequests) {
		return caractiristiqueRequests != null ?
				caractiristiqueRequests
				.stream()
				.map(c->toCaractiristique(c))
				.collect(Collectors.toList())
				: null;
	}

	public static Collection<CaractiristiqueResponse> toCaractiristiqueResponses(
			Collection<Caractiristique> caractiristiques) {
		return caractiristiques != null?
				caractiristiques
				.stream()
				.map(c->toCaractiristiqueResponse(c))
				.collect(Collectors.toList())
				:null;
	}
}
