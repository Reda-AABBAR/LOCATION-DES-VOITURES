package com.locatioVoiture.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locatioVoiture.entities.Agence;

public interface AgenceRepository extends JpaRepository<Agence, Long> {
	@Query("SELECT a FROM Agence a WHERE a.nom = :nom")
    Agence findByNom(String nom);
	
	@Query("SELECT a FROM Agence a WHERE a.ville = :ville")
    Collection<Agence> findByVille(String ville);
}
