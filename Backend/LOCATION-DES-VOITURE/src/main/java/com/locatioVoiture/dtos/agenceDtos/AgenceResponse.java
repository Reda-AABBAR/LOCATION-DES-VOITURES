package com.locatioVoiture.dtos.agenceDtos;

import java.util.Collection;

import com.locatioVoiture.dtos.AdministrateurDtos.AdministrateurResponse;
import com.locatioVoiture.dtos.LivreurDtos.LivreurResponse;
import com.locatioVoiture.dtos.serviceClientDtos.ServiceClientResponse;
import com.locatioVoiture.dtos.vehiculeDtos.VehiculeResponse;

import lombok.Builder;

@Builder
public record AgenceResponse(
		Long id,
		String nom,
		String ville,
		String adresse,
		Collection<AdministrateurResponse> administrateurResponses,
		Collection<LivreurResponse> livreurResponses,
		Collection<ServiceClientResponse> serviceClientResponses,
		Collection<VehiculeResponse> vehiculeResponses
		) {

}
