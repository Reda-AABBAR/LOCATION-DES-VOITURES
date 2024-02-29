package com.locatioVoiture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locatioVoiture.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
