package com.locatioVoiture.dtos.LivreurDtos;

import java.util.Collection;
import java.util.Date;

import com.locatioVoiture.dtos.ReservationDtos.ReservationResponse;
import com.locatioVoiture.entities.Role;

import lombok.Builder;

@Builder
public record LivreurResponse(
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
		Collection<ReservationResponse> reservationResponses
		) {

}
