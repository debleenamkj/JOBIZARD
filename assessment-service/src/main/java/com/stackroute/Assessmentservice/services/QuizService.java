package com.stackroute.Assessmentservice.services;

import com.stackroute.Assessmentservice.model.Category;
import com.stackroute.Assessmentservice.model.Quiz;

import java.util.List;

public interface QuizService {

     public Quiz addQuiz(Quiz quiz);

//    public Quiz updateQuiz(Quiz quiz);

      public List<Quiz> getQuizzes();

//    public Quiz getQuiz(Long quizId);

//    public void deleteQuiz(Long quizId);

//    public List<Quiz> getQuizzesOfCategory(Category category);
}
