package com.locatioVoiture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locatioVoiture.entities.Caractiristique;

public interface caractiristiqueRepository extends JpaRepository<Caractiristique, Long> {

	Caractiristique findByNom(String nom);

}
