package com.stackroute.service;

import com.stackroute.model.exam.Question;
import com.stackroute.model.exam.Quiz;

import java.util.List;
import java.util.Set;

public interface QuestionService {

    public Question addQuestion(Question question);

    public Question updateQuestion(Question question);

    public List<Question> getQuestions();

    public Question getQuestion(Long questionId);

    public Set<Question> getQuestionsOfQuiz(Quiz quiz);

    public void deleteQuestion(Long quesId);

//    public Question get(Long questionsId);

}
