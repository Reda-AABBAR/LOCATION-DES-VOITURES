package com.locatioVoiture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locatioVoiture.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	Utilisateur findByEmail(String email);
}
