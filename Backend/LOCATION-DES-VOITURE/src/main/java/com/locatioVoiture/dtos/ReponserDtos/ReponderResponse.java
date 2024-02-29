package com.locatioVoiture.dtos.ReponserDtos;

import lombok.Builder;

@Builder
public record ReponderResponse(
		Long id,
		String reponse,
		String serviceClientCIN
		) {

}
