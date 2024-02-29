package com.locatioVoiture.mappers.agenceMapper;

import java.util.Collection;
import java.util.stream.Collectors;

import com.locatioVoiture.dtos.agenceDtos.AgenceRequest;
import com.locatioVoiture.dtos.agenceDtos.AgenceResponse;
import com.locatioVoiture.entities.Agence;
import com.locatioVoiture.mappers.administrateurMapper.AdministrateurMapper;
import com.locatioVoiture.mappers.livreurMapper.LivreurMapper;
import com.locatioVoiture.mappers.serviceClientMapper.ServiceClientMapper;
import com.locatioVoiture.mappers.vehiculeMapper.VehiculeMapper;

public class AgenceMapper {
	public static Agence toAgence(AgenceRequest agenceRequest) {
		Agence agence = null;
		
		if(agenceRequest != null) {
			agence = Agence.builder()
					.nom(agenceRequest.nom())
					.ville(agenceRequest.ville())
					.adresse(agenceRequest.adresse())
					.build();
		}
		
		return agence;
	}
	
	public static AgenceResponse toAgenceResponse(Agence agence) {
		AgenceResponse agenceResponse = null;
		
		if(agence != null) {
			agenceResponse = AgenceResponse.builder()
					.administrateurResponses(AdministrateurMapper.toAdministrateurResponses(agence.getAdministrateurs()))
					.adresse(agence.getAdresse())
					.id(agence.getId())
					.livreurResponses(LivreurMapper.toLivreurResponses(agence.getLivreurs()))
					.nom(agence.getNom())
					.serviceClientResponses(ServiceClientMapper.toServiceClientResponses(agence.getServiceClients()))
					.vehiculeResponses(VehiculeMapper.toVehiculeResponses(agence.getVehicules()))
					.ville(agence.getVille())
					.build();
		}
				
		return agenceResponse;
	}

	public static Collection<AgenceResponse> toAgenceResponses(Collection<Agence> agences) {
		
		return agences != null? agences.stream().map(a->toAgenceResponse(a)).collect(Collectors.toList())
				:null;
	}
	
	
}
