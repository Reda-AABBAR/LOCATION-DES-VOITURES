package com.locatioVoiture.mappers.clientMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import com.locatioVoiture.dtos.ClientDtos.ClientRequest;
import com.locatioVoiture.dtos.ClientDtos.ClientResponse;
import com.locatioVoiture.dtos.QuestionDtos.QuestionResponse;
import com.locatioVoiture.dtos.ReservationDtos.ReservationResponse;
import com.locatioVoiture.entities.Client;
import com.locatioVoiture.mappers.questionMapper.QuestionMapper;
import com.locatioVoiture.mappers.reservationMapper.ReservationMapper;

public class ClientMapper {
	
	public static Client toClient(ClientRequest clientRequest) {
		Client client = null;
		
		if(clientRequest != null) {
			client = new Client();
			client.setNom(clientRequest.nom());
			client.setAdresse(clientRequest.adresse());
			client.setCIN(clientRequest.cIN());
			client.setDateNaissance(clientRequest.dateNaissance());
			client.setEmail(clientRequest.email());
			client.setNumTele(clientRequest.numTele());
			client.setPassword(clientRequest.password());
			client.setPrenom(clientRequest.prenom());
		}
		
		return client;
	}
	
	public static ClientResponse toClientResponse(Client client) {
		ClientResponse clientResponse = null;
		Collection<QuestionResponse> questionResponses = new ArrayList<QuestionResponse>();
		Collection<ReservationResponse> reservationResponses = new ArrayList<ReservationResponse>();
		
		if(client != null) {
			
			questionResponses =client.getQuestions() != null ?
					client.getQuestions()
					.stream()
					.map(question -> QuestionMapper.toQuestionResponse(question))
					.collect(Collectors.toList())
					:null;
			
			reservationResponses = client.getReservations() != null ?
					client.getReservations()
					.stream()
					.map(reservation -> ReservationMapper.toReservationResponse(reservation))
					.collect(Collectors.toList())
					: null;
			
			clientResponse = ClientResponse.builder()
					.id(client.getId())
					.adresse(client.getAdresse())
					.cIN(client.getCIN())
					.dateNaissance(client.getDateNaissance())
					.eamil(client.getEmail())
					.nom(client.getNom())
					.numTele(client.getNumTele())
					.password(client.getPassword())
					.prenom(client.getPrenom())
					.reservationResponses(reservationResponses)
					.questionResponses(questionResponses)
					.build();
		}
		
		return clientResponse;
	}
	
	public static ClientResponse toClientResponseFilter(Client client) {
		ClientResponse clientResponse = null;
		Collection<QuestionResponse> questionResponses = new ArrayList<QuestionResponse>();
		Collection<ReservationResponse> reservationResponses = new ArrayList<ReservationResponse>();
		
		if(client != null) {
			clientResponse = ClientResponse.builder()
					.id(client.getId())
					.adresse(client.getAdresse())
					.cIN(client.getCIN())
					.dateNaissance(client.getDateNaissance())
					.eamil(client.getEmail())
					.nom(client.getNom())
					.numTele(client.getNumTele())
					.password(client.getPassword())
					.prenom(client.getPrenom())
					.reservationResponses(reservationResponses)
					.questionResponses(questionResponses)
					.build();
		}
		
		return clientResponse;
	}

	public static Collection<ClientResponse> toClientResponses(Collection<Client> clients) {
		return clients != null ? clients.stream().map(c->toClientResponse(c))
				.collect(Collectors.toList())
				: null;
	}
}
