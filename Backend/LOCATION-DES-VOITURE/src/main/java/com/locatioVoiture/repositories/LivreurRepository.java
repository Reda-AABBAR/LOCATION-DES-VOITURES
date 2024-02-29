package com.locatioVoiture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locatioVoiture.entities.Livreur;

public interface LivreurRepository extends JpaRepository<Livreur, Long>{
	@Query("SELECT a FROM Livreur a WHERE a.cIN = :cIN")
    Livreur findByCIN(String cIN);
	
	@Query("SELECT a FROM Livreur a WHERE a.email = :email")
	Livreur findByEmail(String email);
}
