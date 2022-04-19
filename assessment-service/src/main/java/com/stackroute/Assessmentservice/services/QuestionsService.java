package com.stackroute.Assessmentservice.services;

import com.stackroute.Assessmentservice.Exception.SubCategoryAlreadyExistException;
import com.stackroute.Assessmentservice.Exception.SubCategoryNotExistsException;
import com.stackroute.Assessmentservice.model.Questions;
import com.stackroute.Assessmentservice.model.Quiz;

import java.util.List;
import java.util.Set;

public interface QuestionsService {

    public Questions addQuestion(Questions question) throws SubCategoryNotExistsException;

//    public Questions updateQuestion(Questions question);

//       public List<Questions> getQuestions();

//    public Questions getQuestion(Long questionId);

//      public List<Questions> getQuestionsOfQuiz(Quiz quiz);

//    public void deleteQuestion(Long quesId);
}
