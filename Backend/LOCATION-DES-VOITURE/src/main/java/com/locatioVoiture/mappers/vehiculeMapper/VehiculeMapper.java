package com.locatioVoiture.mappers.vehiculeMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import com.locatioVoiture.dtos.vehiculeDtos.VehiculeRequest;
import com.locatioVoiture.dtos.vehiculeDtos.VehiculeResponse;
import com.locatioVoiture.entities.Photo;
import com.locatioVoiture.entities.Vehicule;
import com.locatioVoiture.mappers.caractiristiqueMapper.CaractiristiqueMapper;
import com.locatioVoiture.mappers.companieMapper.CompanieMapper;
import com.locatioVoiture.mappers.reservationMapper.ReservationMapper;

public class VehiculeMapper {

	public static Vehicule toVehicule(VehiculeRequest vehiculeRequest) {
		Vehicule vehicule = null;
		
		if(vehiculeRequest != null) {
			vehicule = Vehicule.builder().caractiristiques(CaractiristiqueMapper.toCaractiristiques(vehiculeRequest.caractiristiqueRequests()))
					.companie(CompanieMapper.toCompanie(vehiculeRequest.companieRequest()))
					.contrats(vehiculeRequest.contrats())
					.kilometrage(vehiculeRequest.kilometrage())
					.matricule(vehiculeRequest.matricule())
					.modele(vehiculeRequest.modele())
					.modeleDate(vehiculeRequest.modeleDate())
					.prixParJour(vehiculeRequest.prixParJour())
					.build();
			Collection<Photo> photos = vehiculeRequest.photos() != null ?
					vehiculeRequest.photos()
					.stream()
					.map(p-> toImg(p))
					.collect(Collectors.toList())
			: null;
			if(vehicule.getPhotos() != null)	
				vehicule.getPhotos().addAll(photos);
		}
		
		return vehicule;
	}
	
	public static VehiculeResponse toVehiculeResponse(Vehicule vehicule) {
		VehiculeResponse vehiculeResponse = null;
		Collection<byte[]> imgs = new ArrayList<byte[]>();
		
		if(vehicule.getPhotos() != null)
			vehicule.getPhotos().forEach(p->imgs.add(p.getPhotoData()));
		
		if(vehicule != null) {
			vehiculeResponse = VehiculeResponse.builder().
					caractiristiqueResponses(CaractiristiqueMapper.toCaractiristiqueResponses(vehicule.getCaractiristiques()))
					.companieResponse(CompanieMapper.toCompanieResponse(vehicule.getCompanie()))
					.contrats(vehicule.getContrats())
					.id(vehicule.getId())
					.kilometrage(vehicule.getKilometrage())
					.matricule(vehicule.getMatricule())
					.modele(vehicule.getModele())
					.modeleDate(vehicule.getModeleDate())
					.prixParJour(vehicule.getPrixParJour())
					.photos(imgs)
					.reservationResponses(ReservationMapper.toReservationResponses(vehicule.getReservations()))
					.build();			
		}
		return vehiculeResponse;
	}
	
	public static VehiculeResponse toVehiculeResponseFilter(Vehicule vehicule) {
		VehiculeResponse vehiculeResponse = null;
		Collection<byte[]> imgs = new ArrayList<byte[]>();
		
		if(vehicule.getPhotos() != null)
			vehicule.getPhotos().forEach(p->imgs.add(p.getPhotoData()));
		
		if(vehicule != null) {
			vehiculeResponse = VehiculeResponse.builder().
					caractiristiqueResponses(CaractiristiqueMapper.toCaractiristiqueResponses(vehicule.getCaractiristiques()))
					.companieResponse(CompanieMapper.toCompanieResponse(vehicule.getCompanie()))
					.contrats(vehicule.getContrats())
					.id(vehicule.getId())
					.kilometrage(vehicule.getKilometrage())
					.matricule(vehicule.getMatricule())
					.modele(vehicule.getModele())
					.modeleDate(vehicule.getModeleDate())
					.prixParJour(vehicule.getPrixParJour())
					.photos(imgs)
//					.reservationResponses(ReservationMapper.toReservationResponses(vehicule.getReservations()))
					.build();			
		}
		return vehiculeResponse;
	}

	public static Collection<VehiculeResponse> toVehiculeResponses(Collection<Vehicule> vehicules) {
		return vehicules != null ?
				vehicules.stream().map(v->toVehiculeResponse(v))
				.collect(Collectors.toList())
				: null;
	}

	private static Photo toImg(byte[] p) {
		Photo img = new Photo();
		img.setPhotoData(p);
		return img;		
	}


}
