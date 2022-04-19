package com.stackroute.Assessmentservice.services.serviceIMPL;

import com.stackroute.Assessmentservice.Exception.SubCategoryAlreadyExistException;
import com.stackroute.Assessmentservice.Exception.SubCategoryNotExistsException;
import com.stackroute.Assessmentservice.model.Questions;
import com.stackroute.Assessmentservice.model.Quiz;
import com.stackroute.Assessmentservice.repository.QuestionsRepository;
import com.stackroute.Assessmentservice.services.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class QuestionsServiceIMPL implements QuestionsService {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Override
    public Questions addQuestion(Questions question) throws SubCategoryNotExistsException {
        if(questionsRepository.findById(question.getQuesNo()).isPresent()){

            throw new SubCategoryNotExistsException();
        }
        else
            return this.questionsRepository.save(question);

    }

//    @Override
//    public List<Questions> getQuestionsOfQuiz(Quiz quiz) {
//        return (List<Questions>) this.questionsRepository.findByQuiz(quiz);
//    }
}
