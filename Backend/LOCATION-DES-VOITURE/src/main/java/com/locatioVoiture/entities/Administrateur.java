package com.locatioVoiture.entities;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrateur extends Utilisateur{
	@OneToMany(mappedBy = "administrateur",fetch = FetchType.EAGER)
	private Collection<Reservation> reservations;
	
	@ManyToOne
	private Agence agence;
}
