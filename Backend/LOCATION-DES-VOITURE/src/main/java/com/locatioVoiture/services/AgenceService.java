package com.locatioVoiture.services;

import java.util.Collection;

import com.locatioVoiture.dtos.agenceDtos.AgenceRequest;
import com.locatioVoiture.entities.Agence;

public interface AgenceService {
    Agence addAgence(AgenceRequest agenceRequest);
    Agence getAgenceById(Long id);
    Collection<Agence> getAllAgences();
    Agence updateAgence(AgenceRequest agenceRequest, Long id);
    void deleteAgence(Long id);
    Agence searchAgencesByNom(String nom);
    Collection<Agence> searchAgencesByVille(String ville);
}
