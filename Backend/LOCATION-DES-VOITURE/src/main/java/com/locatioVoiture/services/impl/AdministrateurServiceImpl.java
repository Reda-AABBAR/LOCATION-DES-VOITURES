package com.locatioVoiture.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.locatioVoiture.dtos.AdministrateurDtos.AdministrateurRequest;
import com.locatioVoiture.entities.Administrateur;
import com.locatioVoiture.entities.Agence;
import com.locatioVoiture.entities.Role;
import com.locatioVoiture.entities.Utilisateur;
import com.locatioVoiture.mappers.administrateurMapper.AdministrateurMapper;
import com.locatioVoiture.repositories.AdministrateurRepository;
import com.locatioVoiture.repositories.AgenceRepository;
import com.locatioVoiture.services.AdministrateurService;
import com.locatioVoiture.services.AgenceService;

@Service
public class AdministrateurServiceImpl implements AdministrateurService{
	
	@Autowired
	private AdministrateurRepository administrateurRepository;
	
	@Autowired
	private AgenceRepository agenceRepository;
	
	@Override
	public Administrateur addAministrateur(AdministrateurRequest administrateurRequest) {
		Administrateur administrateur = AdministrateurMapper.toAdministrateur(administrateurRequest);
		Agence agence = agenceRepository.findByNom(administrateurRequest.agenceRequest() != null?
				administrateurRequest.agenceRequest().nom()
				: null);
		if(administrateur != null) {
			PasswordEncoder passEncod = new BCryptPasswordEncoder();
			administrateur.setPassword(passEncod.encode(administrateurRequest.password()));
			administrateur.setAgence(agence);
			administrateur.setRoles(new ArrayList<>());
			administrateur.getRoles().add(Role.ADMIN);
		}
		return (administrateur != null)? 
				administrateurRepository.save(administrateur)
				: null;
	}

	@Override
	public Administrateur updateAdministrateur(AdministrateurRequest administrateurRequest, Long id) {
		Administrateur administrateur = AdministrateurMapper.toAdministrateur(administrateurRequest);
		Administrateur adminToUpdate = getAdministrateurById(id);
		if(adminToUpdate != null) {
			administrateur.setId(id);
			administrateur.setRoles(adminToUpdate.getRoles());
			administrateur.setReservations(adminToUpdate.getReservations());
			administrateur.setAgence(agenceRepository.findByNom(administrateurRequest.agenceRequest().nom()));
		return administrateurRepository.save(administrateur);
		}
		return null;
	}

	@Override
	public Collection<Administrateur> getAllAdministrateurs() {
		return administrateurRepository.findAll();
	}

	@Override
	public void deleteAdministrateur(Long id) {
		administrateurRepository.deleteById(id);
	}

	@Override
	public Administrateur getAdministrateurById(Long id) {
		return administrateurRepository.findById(id).orElse(null);
	}

	@Override
	public Administrateur searchAdministrateursByCIN(String cIN) {
		return administrateurRepository.findByCIN(cIN);
	}

	@Override
	public Utilisateur searchAdministrateursByEmail(String email) {
		return administrateurRepository.findByEmail(email);
	}

	@Override
	public Administrateur findByEmail(String email) {
		return administrateurRepository.findByEmail(email);
	}

}
