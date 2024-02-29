package com.locatioVoiture.dtos.QuestionDtos;

import com.locatioVoiture.dtos.ClientDtos.ClientRequest;
import com.locatioVoiture.dtos.ReponserDtos.ReponderRequest;

import lombok.Builder;

@Builder
public record QuestionRequest(
		String question,
		ClientRequest clientRequest,
		ReponderRequest reponderRequest
		) {

}
