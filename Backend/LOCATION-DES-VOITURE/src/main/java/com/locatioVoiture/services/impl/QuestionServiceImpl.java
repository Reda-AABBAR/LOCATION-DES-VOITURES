package com.locatioVoiture.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locatioVoiture.dtos.QuestionDtos.QuestionRequest;
import com.locatioVoiture.entities.Question;
import com.locatioVoiture.mappers.questionMapper.QuestionMapper;
import com.locatioVoiture.mappers.reponderMapper.ReponderMapper;
import com.locatioVoiture.repositories.QuestionRepository;
import com.locatioVoiture.services.ClientService;
import com.locatioVoiture.services.QuestionService;
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private ClientService clientSrevice;

    @Override
    public Question addQuestion(QuestionRequest questionRequest) {
        Question newQuestion = QuestionMapper.toQuestion(questionRequest);
        
        newQuestion.setClient(questionRequest.clientRequest() != null ? clientSrevice.searchClientsByCIN(questionRequest.clientRequest().cIN()) : null);
        newQuestion.setQuestion(questionRequest.question());
        newQuestion.setReponder(ReponderMapper.toReponder(questionRequest.reponderRequest()));
        return questionRepository.save(newQuestion);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question updateQuestion(QuestionRequest questionRequest, Long id) {
        Question existingQuestion = getQuestionById(id);

        if (existingQuestion != null) {
            existingQuestion.setClient(clientSrevice.searchClientsByCIN(questionRequest.clientRequest().cIN()));
            existingQuestion.setQuestion(questionRequest.question());
            existingQuestion.setReponder(existingQuestion.getReponder());

            return questionRepository.save(existingQuestion);
        }

        return null;
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}

