package com.locatioVoiture.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.locatioVoiture.dtos.LivreurDtos.LivreurRequest;
import com.locatioVoiture.entities.Agence;
import com.locatioVoiture.entities.Livreur;
import com.locatioVoiture.entities.Role;
import com.locatioVoiture.mappers.livreurMapper.LivreurMapper;
import com.locatioVoiture.repositories.LivreurRepository;
import com.locatioVoiture.services.AgenceService;
import com.locatioVoiture.services.LivreurService;
@Service
public class LivreurServiceImpl implements LivreurService {

    @Autowired
    private LivreurRepository livreurRepository;
    
    @Autowired
    private AgenceService agenceService;

    @Override
    public Livreur addLivreur(LivreurRequest livreurRequest) {
        Livreur newLivreur = LivreurMapper.toLivreur(livreurRequest);
        Agence agence = livreurRequest.agenceRequest() != null?
        		agenceService.searchAgencesByNom(livreurRequest.agenceRequest().nom())
        		: null;
        if(newLivreur != null) {
        	newLivreur.setPassword(new BCryptPasswordEncoder().encode(livreurRequest.password()));
        	newLivreur.setRoles(new ArrayList<Role>());
        	newLivreur.getRoles().add(Role.LIVREUR);
        	newLivreur.setAgence(agence);
        	return livreurRepository.save(newLivreur);
        }
        return null;
    }

    @Override
    public Livreur getLivreurById(Long id) {
        return livreurRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Livreur> getAllLivreurs() {
        return livreurRepository.findAll();
    }

    @Override
    public Livreur updateLivreur(LivreurRequest livreurRequest, Long id) {
        Livreur livreur = getLivreurById(id);

        if (livreur != null) {
        	livreur.setAdresse(livreurRequest.adresse());
        	livreur.setCIN(livreurRequest.cIN());
        	livreur.setDateNaissance(livreurRequest.dateNaissance());
        	livreur.setEmail(livreurRequest.email());
        	livreur.setNom(livreurRequest.nom());
        	livreur.setNumTele(livreurRequest.numTele());
        	livreur.setPassword(livreurRequest.password());
        	livreur.setPrenom(livreurRequest.prenom());
        	livreur.setAgence(agenceService.searchAgencesByNom(livreurRequest.agenceRequest().nom()));

            return livreurRepository.save(livreur);
        }

        return null;
    }

    @Override
    public void deleteLivreur(Long id) {
        livreurRepository.deleteById(id);
    }

    @Override
    public Livreur searchLivreursByCIN(String cIN) {
        return livreurRepository.findByCIN(cIN);
    }

	@Override
	public Livreur searchLivreursByEmail(String email) {
		return livreurRepository.findByEmail(email);
	}
}

