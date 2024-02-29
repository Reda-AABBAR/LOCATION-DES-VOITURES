package com.locatioVoiture.dtos.ClientDtos;

import java.util.Date;

import lombok.Builder;

@Builder
public record ClientRequest(
		String nom,
		String prenom,
		String cIN,
		Date dateNaissance,
		String adresse,
		String email,
		String numTele,
		String password
		) {

}
