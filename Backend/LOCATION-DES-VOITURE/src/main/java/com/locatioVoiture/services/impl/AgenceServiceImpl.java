package com.locatioVoiture.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locatioVoiture.dtos.agenceDtos.AgenceRequest;
import com.locatioVoiture.entities.Agence;
import com.locatioVoiture.mappers.agenceMapper.AgenceMapper;
import com.locatioVoiture.repositories.AgenceRepository;
import com.locatioVoiture.services.AgenceService;

@Service
public class AgenceServiceImpl implements AgenceService{

	@Autowired
	private AgenceRepository agenceRepository;
	
	@Override
	public Agence addAgence(AgenceRequest agenceRequest) {
		Agence agence = AgenceMapper.toAgence(agenceRequest);
		return agenceRepository.save(agence);
	}

	@Override
	public Agence getAgenceById(Long id) {
		return agenceRepository.findById(id).orElse(null);
	}

	@Override
	public Collection<Agence> getAllAgences() {
		return agenceRepository.findAll();
	}

	@Override
	public Agence updateAgence(AgenceRequest agenceRequest, Long id) {
		Agence agence = AgenceMapper.toAgence(agenceRequest);
		Agence agenceToUpdate = getAgenceById(id);
		if(agence != null && agenceToUpdate != null) {
			agenceToUpdate.setAdresse(agence.getAdresse());
			agenceToUpdate.setNom(agence.getNom());
			agenceToUpdate.setVille(agenceToUpdate.getVille());
			return agenceRepository.save(agenceToUpdate);
		}
		return null;
	}

	@Override
	public void deleteAgence(Long id) {
		agenceRepository.deleteById(id);
	}

	@Override
	public Agence searchAgencesByNom(String nom) {
		return agenceRepository.findByNom(nom);
	}

	@Override
	public Collection<Agence> searchAgencesByVille(String ville) {
		return agenceRepository.findByVille(ville);
	}

}
