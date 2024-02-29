package com.locatioVoiture.mappers.questionMapper;

import java.util.Collection;
import java.util.stream.Collectors;

import com.locatioVoiture.dtos.QuestionDtos.QuestionRequest;
import com.locatioVoiture.dtos.QuestionDtos.QuestionResponse;
import com.locatioVoiture.entities.Question;
import com.locatioVoiture.mappers.clientMapper.ClientMapper;
import com.locatioVoiture.mappers.reponderMapper.ReponderMapper;

public class QuestionMapper {

	public static Question toQuestion(QuestionRequest questionRequest) {
		Question question = null;
		
		if(questionRequest != null) {
			question = Question.builder()
					.client(ClientMapper.toClient(questionRequest.clientRequest()))
					.question(questionRequest.question())
					.reponder(ReponderMapper.toReponder(questionRequest.reponderRequest()))
					.build();
		}
		
		return question;
	}
	
	
	public static QuestionResponse toQuestionResponse(Question question) {
		QuestionResponse questionResponse = null;
		
		if(question != null) {
			questionResponse = QuestionResponse.builder()
					.cINClient(question.getClient() != null ?
							question.getClient().getCIN()
							: null)
					.dateQuestion(question.getDateQuestion())
					.id(question.getId())
					.question(question.getQuestion())
					.reponder(ReponderMapper.toReponderResponse(question.getReponder()))
					.build();
		}
		return questionResponse;
	}


	public static Collection<QuestionResponse> toQuestionResponses(Collection<Question> questions) {
		return questions != null ?
				questions.stream()
				.map(q->toQuestionResponse(q))
				.collect(Collectors.toList())
				: null;
	}

}
