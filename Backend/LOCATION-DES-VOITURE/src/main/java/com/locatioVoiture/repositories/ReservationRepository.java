package com.locatioVoiture.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.locatioVoiture.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	@Query("SELECT r FROM Reservation r WHERE r.estConfirmet = :estConfirmer")
	Collection<Reservation> findByEstCnfirmer(@Param("estConfirmer") boolean estConfirmer);
	
	@Query("SELECT r FROM Reservation r WHERE r.estConfirmet = :estConfirmer AND r.estLivrer = :estLivrer")
	Collection<Reservation> findAllConfirmedNotLivred(@Param("estConfirmer") boolean estConfirmer,
			@Param("estLivrer") boolean estLivrer);
}
