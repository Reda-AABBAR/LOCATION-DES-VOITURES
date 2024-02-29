package com.locatioVoiture.mappers.livreurMapper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import com.locatioVoiture.dtos.LivreurDtos.LivreurRequest;
import com.locatioVoiture.dtos.LivreurDtos.LivreurResponse;
import com.locatioVoiture.dtos.ReservationDtos.ReservationResponse;
import com.locatioVoiture.entities.Client;
import com.locatioVoiture.entities.Livreur;
import com.locatioVoiture.mappers.reservationMapper.ReservationMapper;

public class LivreurMapper {
	
	public static Livreur toLivreur(LivreurRequest livreurRequest) {
		Livreur livreur = null;
		
		if(livreurRequest != null) {
			livreur = new Livreur();
			livreur.setAdresse(livreurRequest.adresse());
			livreur.setCIN(livreurRequest.cIN());
			livreur.setDateNaissance(livreurRequest.dateNaissance());
			livreur.setEmail(livreurRequest.email());
			livreur.setNom(livreurRequest.nom());
			livreur.setNumTele(livreurRequest.numTele());
			livreur.setPassword(livreurRequest.password());
			livreur.setPrenom(livreurRequest.prenom());
		}
		
		return livreur;
	}
	
	public static LivreurResponse toLivreurResponse(Livreur livreur) {
		LivreurResponse livreurResponse = null;
		Collection<ReservationResponse> reservationResponses = new ArrayList<ReservationResponse>();
		
		if(livreur != null) {
			
			reservationResponses = livreur.getReservations() != null ?
					livreur.getReservations()
					.stream()
					.map(reservation -> ReservationMapper.toReservationResponse(reservation))
					.collect(Collectors.toList())
					: null ;
			
			livreurResponse = LivreurResponse.builder()
					.id(livreur.getId())
					.adresse(livreur.getAdresse())
					.cIN(livreur.getCIN())
					.dateNaissance(livreur.getDateNaissance())
					.email(livreur.getEmail())
					.nom(livreur.getNom())
					.numTele(livreur.getNumTele())
					.password(livreur.getPassword())
					.prenom(livreur.getPrenom())
					.reservationResponses(reservationResponses)
					.build();
		}
		
		return livreurResponse;
	}
	
	public static LivreurResponse toLivreurResponseFilter(Livreur livreur) {
		LivreurResponse livreurResponse = null;
		Collection<ReservationResponse> reservationResponses = new ArrayList<ReservationResponse>();
		
		if(livreur != null) {
			livreurResponse = LivreurResponse.builder()
					.id(livreur.getId())
					.adresse(livreur.getAdresse())
					.cIN(livreur.getCIN())
					.dateNaissance(livreur.getDateNaissance())
					.email(livreur.getEmail())
					.nom(livreur.getNom())
					.numTele(livreur.getNumTele())
					.password(livreur.getPassword())
					.prenom(livreur.getPrenom())
					.reservationResponses(reservationResponses)
					.build();
		}
		
		return livreurResponse;
	}

	public static Collection<LivreurResponse> toLivreurResponses(Collection<Livreur> livreurs) {
		return livreurs != null ?
				livreurs.stream().map(livreur->toLivreurResponse(livreur))
				.collect(Collectors.toList())
				: null;
	}
}
