package com.locatioVoiture.dtos.serviceClientDtos;

import java.util.Collection;
import java.util.Date;

import com.locatioVoiture.dtos.ReponserDtos.ReponderResponse;
import com.locatioVoiture.entities.Role;

import lombok.Builder;

@Builder
public record ServiceClientResponse(
		Long id,
		String nom,
		String prenom,
		String cIN,
		Date dateNaissance,
		String adresse,
		String email,
		String numTele,
		String password,
		Collection<Role> roles,
		Collection<ReponderResponse> reponderResponses
		) {

}
