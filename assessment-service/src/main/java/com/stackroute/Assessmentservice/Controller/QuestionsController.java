package com.stackroute.Assessmentservice.Controller;


import com.stackroute.Assessmentservice.Exception.SubCategoryNotExistsException;
import com.stackroute.Assessmentservice.model.Questions;
import com.stackroute.Assessmentservice.model.Quiz;
import com.stackroute.Assessmentservice.services.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ListIterator;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/question")
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;

    @PostMapping("/")
    public ResponseEntity<Questions> addQuestions(@RequestBody Questions questions) throws SubCategoryNotExistsException
    {
        Questions questions1 = this.questionsService.addQuestion(questions);
        return ResponseEntity.ok(questions1);
    }
//    @GetMapping("/")
//    public List<Questions> getQuestions(Quiz quiz)
//    {
//        return this.questionsService.getQuestionsOfQuiz(quiz);
//    }
}
