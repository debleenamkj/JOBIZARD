package com.stackroute.service;

import com.stackroute.model.exam.Category;
import com.stackroute.model.exam.Quiz;

import java.util.List;

public interface QuizService {

    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public List<Quiz> getQuizzes();

    public Quiz getQuiz(Long quizId);

    public void deleteQuiz(Long quizId);


    public List<Quiz> getQuizzesOfCategory(Category category);

    public List<Quiz> getActiveQuizzes();

    public List<Quiz> getActiveQuizzesOfCategory(Category c);
}
