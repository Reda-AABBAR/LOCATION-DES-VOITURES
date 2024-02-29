package com.locatioVoiture.dtos.ClientDtos;

import java.util.Collection;
import java.util.Date;

import com.locatioVoiture.dtos.QuestionDtos.QuestionResponse;
import com.locatioVoiture.dtos.ReservationDtos.ReservationResponse;

import lombok.Builder;

@Builder
public record ClientResponse(
		Long id,
		String nom,
		String prenom,
		String cIN,
		Date dateNaissance,
		String adresse,
		String eamil,
		String numTele,
		String password,
		Collection<QuestionResponse> questionResponses,
		Collection<ReservationResponse> reservationResponses
		) {

}
