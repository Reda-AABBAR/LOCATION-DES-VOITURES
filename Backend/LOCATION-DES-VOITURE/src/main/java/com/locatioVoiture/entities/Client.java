package com.locatioVoiture.entities;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client extends Utilisateur{
	@OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
	private Collection<Question> questions;
	
	@OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
	private Collection<Reservation> reservations;
}
