package com.locatioVoiture.dtos.CompanieDtos;

import lombok.Builder;

@Builder
public record CompanieResponse(
		Long id,
		String nom
		//Collection<VehiculeResponse> vehiculeResponces
		) {

}
