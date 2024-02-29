package com.locatioVoiture.services;

import java.util.Collection;

import com.locatioVoiture.dtos.AdministrateurDtos.AdministrateurRequest;
import com.locatioVoiture.entities.Administrateur;
import com.locatioVoiture.entities.Utilisateur;

public interface AdministrateurService {
	Administrateur addAministrateur(AdministrateurRequest administrateurRequest);
	Administrateur updateAdministrateur(AdministrateurRequest administrateurRequest, Long id);
	Collection<Administrateur> getAllAdministrateurs();
	void deleteAdministrateur(Long id);
	Administrateur getAdministrateurById(Long id);
	Administrateur searchAdministrateursByCIN(String cIN);
	Utilisateur searchAdministrateursByEmail(String email);
	Administrateur findByEmail(String string);	
}
