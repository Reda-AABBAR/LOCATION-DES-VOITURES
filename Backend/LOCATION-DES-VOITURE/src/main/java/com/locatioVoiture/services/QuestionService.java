package com.locatioVoiture.services;

import java.util.Collection;

import com.locatioVoiture.dtos.QuestionDtos.QuestionRequest;
import com.locatioVoiture.entities.Question;

public interface QuestionService {
    Question addQuestion(QuestionRequest questionRequest);
    Question getQuestionById(Long id);
    Collection<Question> getAllQuestions();
    Question updateQuestion(QuestionRequest questionRequest, Long id);
    void deleteQuestion(Long id);
}
