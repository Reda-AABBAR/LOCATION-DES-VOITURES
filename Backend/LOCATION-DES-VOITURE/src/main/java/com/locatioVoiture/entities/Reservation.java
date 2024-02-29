package com.locatioVoiture.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dateDepart;
	private Date dateFin;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateReservation;
	private boolean estContratSegnie;
	private boolean estPaye;
	private boolean estConfirmet;
	private boolean estLivrer;
	
	@ManyToOne
	private Client client;
	
	@ManyToOne
	private Administrateur administrateur;
	
	@ManyToOne
	private Livreur livreur;
	
	@ManyToOne
	private Vehicule vehicule;

	public boolean isValide() {
		if(dateDepart.before(dateFin))
		{
			if(vehicule != null && vehicule.isFree(dateDepart,dateFin)) {
				return true;
			}
		}
		return false;
	}
}
