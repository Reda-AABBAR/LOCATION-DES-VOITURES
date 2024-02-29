package com.locatioVoiture.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locatioVoiture.dtos.QuestionDtos.QuestionRequest;
import com.locatioVoiture.dtos.QuestionDtos.QuestionResponse;
import com.locatioVoiture.entities.Question;
import com.locatioVoiture.mappers.questionMapper.QuestionMapper;
import com.locatioVoiture.services.QuestionService;

@RestController
@RequestMapping("/questions")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionResponse> addQuestion(@RequestBody QuestionRequest questionRequest) {
        Question newQuestion = questionService.addQuestion(questionRequest);
        QuestionResponse questionResponse = QuestionMapper.toQuestionResponse(newQuestion);
        return new ResponseEntity<>(questionResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        QuestionResponse questionResponse = QuestionMapper.toQuestionResponse(question);
        return question != null ? new ResponseEntity<>(questionResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Collection<QuestionResponse>> getAllQuestions() {
        Collection<Question> questions = questionService.getAllQuestions();
        Collection<QuestionResponse> questionResponses = QuestionMapper.toQuestionResponses(questions);
        return new ResponseEntity<>(questionResponses, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponse> updateQuestion(@PathVariable Long id,
                                                          @RequestBody QuestionRequest questionRequest) {
        Question updatedQuestion = questionService.updateQuestion(questionRequest, id);
        QuestionResponse questionResponse = QuestionMapper.toQuestionResponse(updatedQuestion);
        return updatedQuestion != null ? new ResponseEntity<>(questionResponse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

