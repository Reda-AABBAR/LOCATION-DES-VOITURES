package com.locatioVoiture.services;

import java.util.Collection;

import com.locatioVoiture.dtos.LivreurDtos.LivreurRequest;
import com.locatioVoiture.entities.Livreur;

public interface LivreurService {
    Livreur addLivreur(LivreurRequest livreurRequest);
    Livreur getLivreurById(Long id);
    Collection<Livreur> getAllLivreurs();
    Livreur updateLivreur(LivreurRequest livreurRequest, Long id);
    void deleteLivreur(Long id);
    Livreur searchLivreursByCIN(String cIN);
	Livreur searchLivreursByEmail(String email);
}
