package com.locatioVoiture.entities;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String nom;
	private String ville;
	private String adresse;
	
	@OneToMany(mappedBy = "agence",fetch = FetchType.EAGER)
	private Collection<Administrateur> administrateurs = new ArrayList<>();
	
	@OneToMany(mappedBy = "agence",fetch = FetchType.EAGER)
	private Collection<Livreur> livreurs = new ArrayList<>();
	
	@OneToMany(mappedBy = "agence",fetch = FetchType.EAGER)
	private Collection<ServiceClient> serviceClients = new ArrayList<>();
	
	@OneToMany(mappedBy = "agence",fetch = FetchType.EAGER)
	private Collection<Vehicule> vehicules = new ArrayList<>();

}
