package com.locatioVoiture.services;

import java.util.Collection;

import com.locatioVoiture.dtos.CaractiristiqueDtos.CaractiristiqueRequest;
import com.locatioVoiture.entities.Caractiristique;

public interface CaractiristiqueService {
    Caractiristique addCaractiristique(CaractiristiqueRequest caractiristiqueRequest);
    Caractiristique getCaractiristiqueById(Long id);
    Collection<Caractiristique> getAllCaractiristiques();
    Caractiristique updateCaractiristique(CaractiristiqueRequest caractiristiqueRequest, Long id);
    void deleteCaractiristique(Long id);
    Caractiristique searchCaractiristiquesByName(String name);
}
