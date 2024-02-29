package com.locatioVoiture.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locatioVoiture.dtos.CaractiristiqueDtos.CaractiristiqueRequest;
import com.locatioVoiture.entities.Caractiristique;
import com.locatioVoiture.mappers.caractiristiqueMapper.CaractiristiqueMapper;
import com.locatioVoiture.repositories.caractiristiqueRepository;
import com.locatioVoiture.services.CaractiristiqueService;

@Service
public class CaractiristiqueServiceImpl implements CaractiristiqueService{

	@Autowired
	private caractiristiqueRepository caractiristiqueRepository;
	
	@Override
	public Caractiristique addCaractiristique(CaractiristiqueRequest caractiristiqueRequest) {
		Caractiristique caractiristique = CaractiristiqueMapper.toCaractiristique(caractiristiqueRequest);
		return caractiristiqueRepository.save(caractiristique);
	}

	@Override
	public Caractiristique getCaractiristiqueById(Long id) {
		return caractiristiqueRepository.findById(id).orElse(null);
	}

	@Override
	public Collection<Caractiristique> getAllCaractiristiques() {
		return caractiristiqueRepository.findAll();
	}

	@Override
	public Caractiristique updateCaractiristique(CaractiristiqueRequest caractiristiqueRequest, Long id) {
		Caractiristique caractiristique = CaractiristiqueMapper.toCaractiristique(caractiristiqueRequest);
		Caractiristique caractiristiqueToUpdate = getCaractiristiqueById(id);
		if(caractiristique != null && caractiristiqueToUpdate != null) {
			caractiristiqueToUpdate.setNom(caractiristique.getNom());
			return caractiristiqueRepository.save(caractiristiqueToUpdate);
		}
		return null;
	}

	@Override
	public void deleteCaractiristique(Long id) {
		caractiristiqueRepository.deleteById(id);
	}

	@Override
	public Caractiristique searchCaractiristiquesByName(String name) {
		return caractiristiqueRepository.findByNom(name);
	}

}
