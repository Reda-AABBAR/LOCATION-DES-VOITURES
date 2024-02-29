package com.locatioVoiture;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.locatioVoiture.entities.Administrateur;
import com.locatioVoiture.entities.Role;
import com.locatioVoiture.repositories.AdministrateurRepository;

@SpringBootApplication
public class LocationDesVoitureApplication implements CommandLineRunner{

	@Autowired
	private AdministrateurRepository administrateurRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LocationDesVoitureApplication.class, args);
	}
	
	 @Override
	    public void run(String... args) throws Exception {
	        addDefaultUser();
	    }

	    private void addDefaultUser() {
	        if (administrateurRepository.findByEmail("root@root.com") == null) {
	        	Administrateur  administrateur = new Administrateur();
	        	
	        	administrateur.setCIN("0000000");
	        	administrateur.setPassword(new BCryptPasswordEncoder().encode("Test@1234"));
	        	administrateur.setEmail("root@root.com");
	        	administrateur.setRoles(new ArrayList<>());
	        	
	        	administrateur.getRoles().add(Role.ADMIN);
	        	administrateur.getRoles().add(Role.CLIENT);
	        	administrateur.getRoles().add(Role.LIVREUR);
	        	administrateur.getRoles().add(Role.SERVICE_CLIENT);
	        	
	            administrateurRepository.save(administrateur);
	        }
	    }
}
