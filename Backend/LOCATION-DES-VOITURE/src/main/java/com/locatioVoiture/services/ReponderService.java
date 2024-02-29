package com.locatioVoiture.services;

import java.util.Collection;

import com.locatioVoiture.dtos.ReponserDtos.ReponderRequest;
import com.locatioVoiture.entities.Reponder;

public interface ReponderService {
    Reponder addReponder(ReponderRequest reponderRequest);
    Reponder getReponderById(Long id);
    Collection<Reponder> getAllReponders();
    Reponder updateReponder(ReponderRequest reponderRequest, Long id);
    void deleteReponder(Long id);
}
