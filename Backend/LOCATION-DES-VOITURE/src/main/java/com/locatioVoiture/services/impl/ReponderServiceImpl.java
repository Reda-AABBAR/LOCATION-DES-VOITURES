package com.locatioVoiture.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locatioVoiture.dtos.ReponserDtos.ReponderRequest;
import com.locatioVoiture.entities.Reponder;
import com.locatioVoiture.mappers.reponderMapper.ReponderMapper;
import com.locatioVoiture.repositories.ReponderRepository;
import com.locatioVoiture.services.QuestionService;
import com.locatioVoiture.services.ReponderService;
import com.locatioVoiture.services.ServiceClientService;
@Service
public class ReponderServiceImpl implements ReponderService {

    @Autowired
    private ReponderRepository reponderRepository;
    
    @Autowired
    private ServiceClientService serviceClientService;
    
    @Autowired
    private QuestionService questionService;

    @Override
    public Reponder addReponder(ReponderRequest reponderRequest) {
        Reponder newReponder = ReponderMapper.toReponder(reponderRequest);
        newReponder.setQuestion(questionService.getQuestionById(reponderRequest.questionId()));
        newReponder.setServiceClient(reponderRequest.serviceClientRequest() != null ?
        		serviceClientService.getServiceClientByCIN(reponderRequest.serviceClientRequest().cIN())
        		: null);
        newReponder.setQuestion(questionService.getQuestionById(reponderRequest.questionId()));
        return reponderRepository.save(newReponder);
    }

    @Override
    public Reponder getReponderById(Long id) {
        return reponderRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Reponder> getAllReponders() {
        return reponderRepository.findAll();
    }

    @Override
    public Reponder updateReponder(ReponderRequest reponderRequest, Long id) {
        Reponder existingReponder = getReponderById(id);
        Reponder reponder = ReponderMapper.toReponder(reponderRequest);
        reponder.setQuestion(questionService.getQuestionById(reponderRequest.questionId()));
        if (existingReponder != null) {
            reponder.setDateReponse(existingReponder.getDateReponse());
            reponder.setId(id);
            reponder.setServiceClient(reponderRequest.serviceClientRequest() != null ?
            		serviceClientService.getServiceClientByCIN(reponderRequest.serviceClientRequest().cIN())
            		: null);
            reponder.setQuestion(existingReponder.getQuestion());
            return reponderRepository.save(reponder);
        }

        return null;
    }

    @Override
    public void deleteReponder(Long id) {
        reponderRepository.deleteById(id);
    }
}
