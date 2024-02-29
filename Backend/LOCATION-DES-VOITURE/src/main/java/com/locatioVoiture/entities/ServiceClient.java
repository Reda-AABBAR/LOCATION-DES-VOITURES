package com.locatioVoiture.entities;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceClient extends Utilisateur{
	@OneToMany(mappedBy = "serviceClient",fetch = FetchType.EAGER)
	private Collection<Reponder> reponses;
	
	@ManyToOne
	private Agence agence;
}
