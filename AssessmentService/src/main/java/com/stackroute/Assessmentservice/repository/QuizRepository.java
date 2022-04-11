package com.stackroute.Assessmentservice.repository;

import com.stackroute.Assessmentservice.model.Category;
import com.stackroute.Assessmentservice.model.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface QuizRepository extends MongoRepository<Quiz,Integer> {

}
