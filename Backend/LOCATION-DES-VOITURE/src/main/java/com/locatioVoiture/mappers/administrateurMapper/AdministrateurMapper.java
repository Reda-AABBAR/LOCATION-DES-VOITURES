package com.locatioVoiture.mappers.administrateurMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import com.locatioVoiture.dtos.AdministrateurDtos.AdministrateurRequest;
import com.locatioVoiture.dtos.AdministrateurDtos.AdministrateurResponse;
import com.locatioVoiture.dtos.ReservationDtos.ReservationResponse;
import com.locatioVoiture.entities.Administrateur;
import com.locatioVoiture.entities.Utilisateur;
import com.locatioVoiture.mappers.reservationMapper.ReservationMapper;

public class AdministrateurMapper {
	
	
	
	public static Administrateur toAdministrateur(AdministrateurRequest administrateurRequest) {
		Administrateur administrateur = null;
		if(administrateurRequest != null) {
			administrateur = new Administrateur();
			administrateur.setNom(administrateurRequest.nom());
			administrateur.setPrenom(administrateurRequest.prenom());
			administrateur.setAdresse(administrateurRequest.adresse());
			administrateur.setCIN(administrateurRequest.cIN());
			administrateur.setDateNaissance(administrateurRequest.dateNaissance());
			administrateur.setEmail(administrateurRequest.email());
			administrateur.setNumTele(administrateurRequest.numTele());
			administrateur.setPassword(administrateurRequest.password());
		}
		return administrateur;
	}
	
	public static AdministrateurResponse toAdministrateurResponse(Administrateur administrateur) {
		AdministrateurResponse administrateurResponse = null;
		Collection<ReservationResponse> reservationResponses = new ArrayList<ReservationResponse>();
		if(administrateur != null) {
			
			reservationResponses = administrateur.getReservations() != null ?
					administrateur.getReservations()
	                .stream()
	                .map(r->ReservationMapper.toReservationResponse(r))
	                .collect( Collectors.toList())
	                : null;
			
			administrateurResponse = AdministrateurResponse.builder()
					.adresse(administrateur.getAdresse())
					.cIN(administrateur.getCIN())
					.dateNaissance(administrateur.getDateNaissance())
					.email(administrateur.getEmail())
					.id(administrateur.getId())
					.nom(administrateur.getNom())
					.numTele(administrateur.getNumTele())
					.password(administrateur.getPassword())
					.prenom(administrateur.getPrenom())
					.roles(administrateur.getRoles())
					.reservationResponses(reservationResponses)
					.build();
		}
		return administrateurResponse;
	}


	public static AdministrateurResponse toAdministrateurResponsefilter(Administrateur administrateur) {
		AdministrateurResponse administrateurResponse = null;
		Collection<ReservationResponse> reservationResponses = new ArrayList<ReservationResponse>();
		if(administrateur != null) {
			
			
			administrateurResponse = AdministrateurResponse.builder()
					.adresse(administrateur.getAdresse())
					.cIN(administrateur.getCIN())
					.dateNaissance(administrateur.getDateNaissance())
					.email(administrateur.getEmail())
					.id(administrateur.getId())
					.nom(administrateur.getNom())
					.numTele(administrateur.getNumTele())
					.password(administrateur.getPassword())
					.prenom(administrateur.getPrenom())
					.roles(administrateur.getRoles())
					.reservationResponses(reservationResponses)
					.build();
		}
		return administrateurResponse;
	}
	
	public static Collection<AdministrateurResponse> toAdministrateurResponses(
			Collection<Administrateur> administrateurs) {
		if(administrateurs != null)
		return administrateurs.stream().map(admin->toAdministrateurResponse(admin))
				.collect(Collectors.toList());
		return null;
	}
}
