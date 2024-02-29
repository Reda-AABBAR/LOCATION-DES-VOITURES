package com.locatioVoiture.services.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.locatioVoiture.dtos.vehiculeDtos.VehiculeRequest;
import com.locatioVoiture.entities.Caractiristique;
import com.locatioVoiture.entities.Photo;
import com.locatioVoiture.entities.Vehicule;
import com.locatioVoiture.mappers.vehiculeMapper.VehiculeMapper;
import com.locatioVoiture.repositories.PhotoRepository;
import com.locatioVoiture.repositories.VehiculeRepository;
import com.locatioVoiture.services.AgenceService;
import com.locatioVoiture.services.CaractiristiqueService;
import com.locatioVoiture.services.CompanieService;
import com.locatioVoiture.services.VehiculeService;
@Service
public class VehiculeServiceImpl implements VehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;
    
    @Autowired
    private CaractiristiqueService caractiristiqueService;
    
    @Autowired
    private CompanieService companieService;
    
    @Autowired
    private AgenceService agenceService;
    
    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Vehicule addVehicule(VehiculeRequest vehiculeRequest) {
        Vehicule newVehicule = VehiculeMapper.toVehicule(vehiculeRequest);
        Collection<Caractiristique> caractiristiques = vehiculeRequest.caractiristiqueRequests() != null ?
        		vehiculeRequest.caractiristiqueRequests().stream()
        		.map(c->caractiristiqueService.searchCaractiristiquesByName(c.nom()))
        		.collect(Collectors.toList())
        		: null;
        newVehicule.setCaractiristiques(caractiristiques);
        
        newVehicule.setCompanie(vehiculeRequest.companieRequest() != null ?
        		companieService.searchCompaniesByNom(vehiculeRequest.companieRequest().nom())
        		: null);
        newVehicule.setAgence(vehiculeRequest.agenceRequest() != null ?
        		agenceService.searchAgencesByNom(vehiculeRequest.agenceRequest().nom())
        		: null);
        
        return vehiculeRepository.save(newVehicule);
    }

    @Override
    public Vehicule getVehiculeById(Long id) {
        return vehiculeRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule updateVehicule(VehiculeRequest vehiculeRequest, Long id) {
        Vehicule existingVehicule = getVehiculeById(id);
        Vehicule vehicule = VehiculeMapper.toVehicule(vehiculeRequest);
        Collection<Caractiristique> caractiristiques = vehiculeRequest.caractiristiqueRequests() != null ?
        		vehiculeRequest.caractiristiqueRequests().stream()
        		.map(c->caractiristiqueService.searchCaractiristiquesByName(c.nom()))
        		.collect(Collectors.toList())
        		: null;
        if (existingVehicule != null) {
            vehicule.setCaractiristiques(caractiristiques);
            vehicule.setId(id);
            
            vehicule.setCompanie(vehiculeRequest.companieRequest() != null ?
            		companieService.searchCompaniesByNom(vehiculeRequest.companieRequest().nom())
            		: null);
            vehicule.setAgence(vehiculeRequest.agenceRequest() != null ?
            		agenceService.searchAgencesByNom(vehiculeRequest.agenceRequest().nom())
            		: null);
            
            vehicule.setReservations(existingVehicule.getReservations());
            return vehiculeRepository.save(vehicule);
        }

        return null;
    }

    @Override
    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }

    @Override
    public Vehicule searchVehiculesByMatricule(String matricule) {
        return vehiculeRepository.findByMatricule(matricule);
    }
    
    @Override
    public String setImageToVehicule(Long id , MultipartFile file) {
    	if (!file.isEmpty()) {
    		Vehicule vehicule = getVehiculeById(id);
            if(vehicule != null) {
            	try {
                    byte[] imageData = file.getBytes();
                    Photo photo = new Photo();
                    photo.setPhotoData(imageData);
                    vehicule.getPhotos().add(photo);
                    photo.setVehicule(vehicule);
                    photoRepository.save(photo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return "CREATED";
            }else {
            	return "NOT_FOUND";
            }
        }
    	return "EMPTY";
	}
}

