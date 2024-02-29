package com.locatioVoiture.mappers.companieMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import com.locatioVoiture.dtos.CompanieDtos.CompanieRequest;
import com.locatioVoiture.dtos.CompanieDtos.CompanieResponse;
import com.locatioVoiture.dtos.vehiculeDtos.VehiculeResponse;
import com.locatioVoiture.entities.Companie;
import com.locatioVoiture.mappers.vehiculeMapper.VehiculeMapper;

public class CompanieMapper {
	public static Companie toCompanie(CompanieRequest companieRequest) {
		Companie companie = null;
		
		if(companieRequest != null) {
			companie = Companie.builder()
					.nom(companieRequest.nom())
					.build();
		}
		
		return companie;
	}
	
	public static CompanieResponse toCompanieResponse(Companie companie) {
		CompanieResponse companieResponse = null;
		Collection<VehiculeResponse> vehiculeResponce = new ArrayList<VehiculeResponse>();
		if(companie != null) {
//			
//			vehiculeResponce = companie.getVehicules() != null ?
//					companie.getVehicules()
//					.stream()
//					.map(vehicule -> VehiculeMapper.toVehiculeResponse(vehicule))
//					.collect(Collectors.toList())
//					: null;
			
			companieResponse = CompanieResponse.builder()
					.id(companie.getId())
					.nom(companie.getNom())
//					.vehiculeResponces(vehiculeResponce)
					.build();
		}
		
		return companieResponse;
	}

	public static Collection<CompanieResponse> toCompanieResponses(Collection<Companie> companies) {
		return companies != null ? 
				companies.stream().map(c->toCompanieResponse(c)).collect(Collectors.toList())
				: null;
	}
}
