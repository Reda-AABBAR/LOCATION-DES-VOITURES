package com.locatioVoiture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.locatioVoiture.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	@Query("SELECT a FROM Client a WHERE a.cIN = :cIN")
    Client findByCIN(@Param("cIN") String cIN);
	
	@Query("SELECT a FROM Client a WHERE a.email = :email")
	Client findByEmail(String email);
}
