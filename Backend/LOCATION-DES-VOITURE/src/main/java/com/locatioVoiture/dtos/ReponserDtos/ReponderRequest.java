package com.locatioVoiture.dtos.ReponserDtos;

import com.locatioVoiture.dtos.serviceClientDtos.ServiceClientRequest;

import lombok.Builder;

@Builder
public record ReponderRequest(
		String reponse,
		ServiceClientRequest serviceClientRequest,
		Long questionId
		) {

}
