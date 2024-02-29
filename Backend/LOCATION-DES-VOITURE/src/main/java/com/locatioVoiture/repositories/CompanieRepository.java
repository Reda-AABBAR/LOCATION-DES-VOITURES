package com.locatioVoiture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locatioVoiture.entities.Companie;

public interface CompanieRepository extends JpaRepository<Companie, Long> {

	Companie findByNom(String nom);

}
