package com.locatioVoiture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locatioVoiture.entities.Administrateur;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Long>{
	@Query("SELECT a FROM Administrateur a WHERE a.cIN = :cIN")
    Administrateur findByCIN(String cIN);
	
	@Query("SELECT a FROM Administrateur a WHERE a.email = :email")
    Administrateur findByEmail(String email);
}
