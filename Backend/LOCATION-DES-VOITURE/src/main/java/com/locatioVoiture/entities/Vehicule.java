package com.locatioVoiture.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String matricule;
	private String modele;
	private Double prixParJour;
	private Double kilometrage;
	private Date modeleDate;
	
	@OneToMany(mappedBy = "vehicule",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Collection<Photo> photos = new ArrayList<Photo>();
	
	@ElementCollection
	private Collection<String> contrats;
	
	@OneToMany(mappedBy = "vehicule",fetch = FetchType.EAGER)
	private Collection<Reservation> reservations;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Caractiristique> caractiristiques;
	
	@ManyToOne
	private Companie companie;
	
	@ManyToOne
	private Agence agence;

	public boolean isFree(Date dateDepart, Date dateFin) {
		for (Reservation reservation : reservations) {
			if(reservation != null) {
				Date debut = reservation.getDateDepart();
				Date fin = reservation.getDateFin();
				if(!((dateDepart.before(debut) && dateFin.before(debut)) || (dateDepart.after(fin) && dateFin.after(fin)) )) {
					return false;
				}
			}
		}
		return true;
	}
	
	 @PreRemove
	    private void preRemove() {
	        if (caractiristiques != null) {
	            caractiristiques.clear();
	        }
	    }
}
