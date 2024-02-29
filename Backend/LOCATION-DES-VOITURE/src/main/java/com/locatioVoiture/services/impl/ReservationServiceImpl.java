package com.locatioVoiture.services.impl;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locatioVoiture.dtos.ReservationDtos.ReservationRequest;
import com.locatioVoiture.entities.Reservation;
import com.locatioVoiture.entities.Vehicule;
import com.locatioVoiture.mappers.reservationMapper.ReservationMapper;
import com.locatioVoiture.repositories.ReservationRepository;
import com.locatioVoiture.services.AdministrateurService;
import com.locatioVoiture.services.ClientService;
import com.locatioVoiture.services.LivreurService;
import com.locatioVoiture.services.ReservationService;
import com.locatioVoiture.services.VehiculeService;
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private ClientService clientService;
    
    @Autowired
    private AdministrateurService administrateurService;
    
    @Autowired
    private VehiculeService vehiculeService;
    
    @Autowired
    private LivreurService livreurService;

    @Override
    public Reservation addReservation(ReservationRequest reservationRequest) {
        Reservation newReservation = ReservationMapper.toReservation(reservationRequest);
        if(reservationRequest != null) {
        	if(reservationRequest.dateDebut().before(reservationRequest.dateFin())) {
	        	newReservation.setClient(reservationRequest.clientRequest() != null ?
	        			clientService.searchClientsByCIN(reservationRequest.clientRequest().cIN())
	        			: null );
	        	newReservation.setLivreur(reservationRequest.livreurRequest() != null ?
	        			livreurService.searchLivreursByCIN(reservationRequest.livreurRequest().cIN())
	        			: null);
	        	newReservation.setVehicule(reservationRequest.vehiculeRequest() != null?
	        			vehiculeService.searchVehiculesByMatricule(reservationRequest.vehiculeRequest().matricule())
	        			: null);
	        	newReservation.setAdministrateur(reservationRequest.administrateurRequest() != null ?
	        			administrateurService.searchAdministrateursByCIN(reservationRequest.administrateurRequest().cIN())
	        			: null);
    	        
    	        if(newReservation.isValide()) {
    	        	System.out.println("ok");
    	        	return reservationRepository.save(newReservation);
    	        }
            }
        }
        return null;
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(ReservationRequest reservationRequest, Long id) {
        Reservation existingReservation = getReservationById(id);

        if (existingReservation != null) {
        	
        	existingReservation.setDateDepart(reservationRequest.dateDebut());
        	existingReservation.setDateFin(reservationRequest.dateFin());
        	
        	existingReservation.setEstConfirmet(reservationRequest.estConfirmet());
        	existingReservation.setEstContratSegnie(reservationRequest.estContratSegnie());
        	existingReservation.setEstLivrer(reservationRequest.estLivrer());
        	existingReservation.setEstPaye(reservationRequest.estPaye());
        	
        	if(reservationRequest.clientRequest() != null)
        		 existingReservation.setClient(clientService.searchClientsByCIN(reservationRequest.clientRequest().cIN()));
 	        if(reservationRequest.livreurRequest() != null)
 	        	existingReservation.setLivreur(livreurService.searchLivreursByCIN(reservationRequest.livreurRequest().cIN()));
 	        if(reservationRequest.vehiculeRequest() != null)	
 	        	existingReservation.setVehicule(vehiculeService.searchVehiculesByMatricule(reservationRequest.vehiculeRequest().matricule()));
 	        if(reservationRequest.administrateurRequest() != null)
 	        	existingReservation.setAdministrateur(administrateurService.searchAdministrateursByCIN(reservationRequest.administrateurRequest().cIN()));
 	        
            return reservationRepository.save(existingReservation);
        }

        return null;
    }

    @Override
    public boolean deleteReservation(Long id) {
        Reservation reservation = getReservationById(id);
        Date dateDepart = null;
        if(reservation != null) {
        	dateDepart = reservation.getDateDepart();
        	if(dateDepart != null && dateDepart.before(new Date())) {
        		reservationRepository.delete(reservation);
        		return true;
        	}
        }
        return false;
    }
    @Override
    public Reservation confirmerReservation(Long id) {
    	Reservation reservation = reservationRepository.findById(id).orElse(null);
    	if(reservation != null) {
    		reservation.setEstConfirmet(true);
    		return reservationRepository.save(reservation);
    	}
    	return null;
    }
    
    @Override
    public Reservation livrerReservation(Long id) {
    	Reservation reservation = reservationRepository.findById(id).orElse(null);
    	if(reservation != null) {
    		reservation.setEstLivrer(true);
    		return reservationRepository.save(reservation);
    	}
    	return null;
    }
    
    @Override
    public Reservation payerReservation(Long id) {
    	Reservation reservation = reservationRepository.findById(id).orElse(null);
    	if(reservation != null) {
    		reservation.setEstPaye(true);
    		return reservationRepository.save(reservation);
    	}
    	return null;
    }
    
    @Override
    public Collection<Reservation> getNotConfermedReservation() {
    	return reservationRepository.findAllConfirmedNotLivred(false, false);
	}

	@Override
	public Collection<Reservation> getAllConfermedNotLivred() {
		return reservationRepository.findAllConfirmedNotLivred(true,false);
	}
}

