package com.locatioVoiture.services;

import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

import com.locatioVoiture.dtos.vehiculeDtos.VehiculeRequest;
import com.locatioVoiture.entities.Vehicule;

public interface VehiculeService {
    Vehicule addVehicule(VehiculeRequest vehiculeRequest);
    Vehicule getVehiculeById(Long id);
    Collection<Vehicule> getAllVehicules();
    Vehicule updateVehicule(VehiculeRequest vehiculeRequest, Long id);
    void deleteVehicule(Long id);
    Vehicule searchVehiculesByMatricule(String matricule);
	String setImageToVehicule(Long id, MultipartFile file);
}
