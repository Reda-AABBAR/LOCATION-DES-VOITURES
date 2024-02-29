package com.locatioVoiture.dtos.AdministrateurDtos;

import java.util.Date;

import com.locatioVoiture.dtos.agenceDtos.AgenceRequest;

import lombok.Builder;

@Builder
public record AdministrateurRequest(
		String nom,
		String prenom,
		String cIN,
		Date dateNaissance,
		String email,
		String adresse,
		String numTele,
		String password,
		AgenceRequest agenceRequest
		) {

}
