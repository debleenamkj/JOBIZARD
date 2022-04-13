package com.stackroute.Assessmentservice.services.serviceIMPL;

import com.stackroute.Assessmentservice.model.Category;
import com.stackroute.Assessmentservice.model.Quiz;
import com.stackroute.Assessmentservice.repository.QuizRepository;
import com.stackroute.Assessmentservice.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceIMPL implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getQuizzes() {
        return this.quizRepository.findAll();
    }

//    @Override
//    public List<Quiz> getQuizzesOfCategory(Category category) {
//        return this.quizRepository.findbycategory(category);
//    }
}
