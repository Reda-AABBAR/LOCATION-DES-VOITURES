package com.locatioVoiture.security;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.locatioVoiture.entities.Utilisateur;
import com.locatioVoiture.services.AdministrateurService;
import com.locatioVoiture.services.ClientService;
import com.locatioVoiture.services.LivreurService;
import com.locatioVoiture.services.ServiceClientService;
import com.nimbusds.jose.jwk.source.ImmutableSecret;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfiguration{

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AdministrateurService administrateurService;
	
	@Autowired
	private LivreurService livreurService;
	
	@Autowired
	private ServiceClientService serviceClientService;
	
	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		
		return new InMemoryUserDetailsManager(
				User.builder()
				.password(new BCryptPasswordEncoder().encode("Test@1234"))
				.username("root")
				.authorities("ADMIN","CLIENT","LIVREUR","SERVICE_CLIENT")
				.build()
				);
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(c->c.disable())
				.oauth2ResourceServer(ao->ao.jwt(Customizer.withDefaults()))
				.authorizeHttpRequests(ar->ar.requestMatchers("/auth/**").permitAll())
				.authorizeHttpRequests(ar->ar.anyRequest().authenticated())
				.build();
	}
	
	@Bean
	JwtEncoder jwtEncoder(@Value("${jwt.secret}") String key) {
		return new NimbusJwtEncoder(new ImmutableSecret<>(key.getBytes()));
	}
	
	@Bean
	JwtDecoder jwtDecoder(@Value("${jwt.secret}") String key) {
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "RSA");
		return NimbusJwtDecoder.withSecretKey(keySpec).macAlgorithm(MacAlgorithm.HS512).build();
	}
	
	private UserDetails getUserByEmail(String email) {
		Utilisateur util = null;
		UserDetails user = null;
		util = administrateurService.searchAdministrateursByEmail(email);
		if(util == null)
			util = clientService.searchClientsByEmail(email);
		if(util == null)
			util = serviceClientService.searchByEmail(email);
		if(util == null)
			util = livreurService.searchLivreursByEmail(email);
		if(util != null) {
			Collection<GrantedAuthority> roles = util.getRoles()
					.stream()
					.map(r-> new SimpleGrantedAuthority(r.name()))
					.collect(Collectors.toList());
			user = User.builder()
					.password(util.getPassword())
					.username(util.getEmail())
					.authorities(roles)
					.build();
		}
		return user;
	}
	
	@Bean
	AuthenticationManager authenticationManager(/*UserDetailsService userDetailsService*/) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setUserDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return getUserByEmail(username);
			}
		});
		
		return new ProviderManager(authenticationProvider);
	}
}
