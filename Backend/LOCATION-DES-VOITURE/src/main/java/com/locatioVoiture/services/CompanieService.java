package com.locatioVoiture.services;

import java.util.Collection;

import com.locatioVoiture.dtos.CompanieDtos.CompanieRequest;
import com.locatioVoiture.entities.Companie;

public interface CompanieService {
    Companie addCompanie(CompanieRequest companieRequest);
    Companie getCompanieById(Long id);
    Collection<Companie> getAllCompanies();
    Companie updateCompanie(CompanieRequest companieRequest, Long id);
    void deleteCompanie(Long id);
    Companie searchCompaniesByNom(String nom);
}
