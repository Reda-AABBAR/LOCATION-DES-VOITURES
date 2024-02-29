package com.locatioVoiture.dtos.QuestionDtos;

import java.util.Date;

import com.locatioVoiture.dtos.ReponserDtos.ReponderResponse;

import lombok.Builder;

@Builder
public record QuestionResponse(
		Long id,
		String question,
		Date dateQuestion,
		String cINClient,
		ReponderResponse reponder
		) {

}
