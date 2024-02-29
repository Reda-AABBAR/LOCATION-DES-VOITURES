package com.locatioVoiture.dtos.CaractiristiqueDtos;

import lombok.Builder;

@Builder
public record CaractiristiqueResponse(
		Long id,
		String nom
//		Collection<VehiculeResponse> vehiculeResponces
		) {

}
