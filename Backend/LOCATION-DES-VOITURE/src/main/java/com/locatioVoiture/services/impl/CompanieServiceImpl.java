package com.locatioVoiture.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locatioVoiture.dtos.CompanieDtos.CompanieRequest;
import com.locatioVoiture.entities.Companie;
import com.locatioVoiture.mappers.companieMapper.CompanieMapper;
import com.locatioVoiture.repositories.CompanieRepository;
import com.locatioVoiture.services.CompanieService;

@Service
public class CompanieServiceImpl implements CompanieService {

    @Autowired
    private CompanieRepository companieRepository;

    @Override
    public Companie addCompanie(CompanieRequest companieRequest) {
        Companie companie = CompanieMapper.toCompanie(companieRequest);
        return companieRepository.save(companie);
    }

    @Override
    public Companie getCompanieById(Long id) {
        return companieRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Companie> getAllCompanies() {
        return companieRepository.findAll();
    }

    @Override
    public Companie updateCompanie(CompanieRequest companieRequest, Long id) {
        Companie companie = getCompanieById(id);
        if (companie != null) {
        	companie.setNom(companieRequest.nom());
            return companieRepository.save(companie);
        }
        return null;
    }

    @Override
    public void deleteCompanie(Long id) {
        companieRepository.deleteById(id);
    }

    @Override
    public Companie searchCompaniesByNom(String nom) {
        return companieRepository.findByNom(nom);
    }
}

