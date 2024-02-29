package com.locatioVoiture.controllers;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.locatioVoiture.dtos.AdministrateurDtos.AdministrateurRequest;
import com.locatioVoiture.dtos.ClientDtos.ClientRequest;
import com.locatioVoiture.entities.Administrateur;
import com.locatioVoiture.entities.Client;
import com.locatioVoiture.repositories.UtilisateurRepository;
import com.locatioVoiture.services.AdministrateurService;
import com.locatioVoiture.services.ClientService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class SecurityController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private AdministrateurService administrateurService;

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private JwtEncoder jwtEncoder;
	
	@PostMapping("/login")
	public Map<String,String> login(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, password)
		);
		Instant instance = Instant.now();
		String scope = authentication.getAuthorities().
				stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));
		JwtClaimsSet claimsSet = JwtClaimsSet.builder()
				.issuedAt(instance)
				.expiresAt(instance.plus(10, ChronoUnit.MINUTES))
				.subject(email)
				.claim("scope", scope)
				.build();
		JwtEncoderParameters encoderParameters = 
				JwtEncoderParameters.from(
						JwsHeader.with(MacAlgorithm.HS512).build()
						, claimsSet
						);
		String jwt = jwtEncoder.encode(encoderParameters).getTokenValue();
		return Map.of("access-token",jwt);
	}
	
	@PostMapping("/register/admin")
	public ResponseEntity<?> registerAdmin(@RequestBody AdministrateurRequest signUpRequest) {
		 if (utilisateurRepository.findByEmail(signUpRequest.email()) != null) {
	            return new ResponseEntity<>("Email Address already in use!",
	                    HttpStatus.BAD_REQUEST);
	        }
		 	Administrateur administrateur = administrateurService.addAministrateur(signUpRequest);
		 	if(administrateur != null) {
		 	Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(administrateur.getEmail(), signUpRequest.password())
			);
		 	Instant instance = Instant.now();
			String scope = authentication.getAuthorities().
					stream()
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.joining(" "));
			JwtClaimsSet claimsSet = JwtClaimsSet.builder()
					.issuedAt(instance)
					.expiresAt(instance.plus(10, ChronoUnit.MINUTES))
					.subject(administrateur.getEmail())
					.claim("scope", scope)
					.build();
			JwtEncoderParameters encoderParameters = 
					JwtEncoderParameters.from(
							JwsHeader.with(MacAlgorithm.HS512).build()
							, claimsSet
							);
			String jwt = jwtEncoder.encode(encoderParameters).getTokenValue();
		 	
		 	return ResponseEntity.ok(Map.of("access-token",jwt));
		 	}
		 	return new ResponseEntity<>("Accont did not created!",
                    HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/register/client")
	public ResponseEntity<?> registerClient(@RequestBody ClientRequest signUpRequest) {
		 if (utilisateurRepository.findByEmail(signUpRequest.email()) != null) {
	            return new ResponseEntity<>("Email Address already in use!",
	                    HttpStatus.BAD_REQUEST);
	        }
		 	Client client = clientService.addClient(signUpRequest);
		 	if(client != null) {
		 	Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(client.getEmail(), signUpRequest.password())
			);
		 	Instant instance = Instant.now();
			String scope = authentication.getAuthorities().
					stream()
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.joining(" "));
			JwtClaimsSet claimsSet = JwtClaimsSet.builder()
					.issuedAt(instance)
					.expiresAt(instance.plus(10, ChronoUnit.MINUTES))
					.subject(client.getEmail())
					.claim("scope", scope)
					.build();
			JwtEncoderParameters encoderParameters = 
					JwtEncoderParameters.from(
							JwsHeader.with(MacAlgorithm.HS512).build()
							, claimsSet
							);
			String jwt = jwtEncoder.encode(encoderParameters).getTokenValue();
		 	
		 	return ResponseEntity.ok(Map.of("access-token",jwt));
		 	}
		 	return new ResponseEntity<>("Accont did not created!",
                 HttpStatus.BAD_REQUEST);
		}
}
