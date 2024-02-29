package com.locatioVoiture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.locatioVoiture.entities.ServiceClient;

public interface serviceClientRepository extends JpaRepository<ServiceClient, Long>{
	@Query("SELECT a FROM ServiceClient a WHERE a.cIN = :cIN")
    ServiceClient findByCIN(String cIN);
	
	@Query("SELECT a FROM ServiceClient a WHERE a.email = :email")
	ServiceClient findByEmail(String email);
}
