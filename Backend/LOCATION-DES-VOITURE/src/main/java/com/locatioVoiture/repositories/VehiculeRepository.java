package com.locatioVoiture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locatioVoiture.entities.Vehicule;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long>{

	Vehicule findByMatricule(String matricule);

}
