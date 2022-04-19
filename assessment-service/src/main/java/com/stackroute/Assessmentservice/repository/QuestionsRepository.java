package com.stackroute.Assessmentservice.repository;

import com.stackroute.Assessmentservice.model.Questions;
import com.stackroute.Assessmentservice.model.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;

public interface QuestionsRepository extends MongoRepository<Questions,Integer> {
//    Set<Questions> findByQuiz(Quiz quiz);
}
