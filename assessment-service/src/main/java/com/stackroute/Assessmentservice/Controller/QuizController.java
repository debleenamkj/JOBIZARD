package com.stackroute.Assessmentservice.Controller;


import com.stackroute.Assessmentservice.model.Category;
import com.stackroute.Assessmentservice.model.Quiz;
import com.stackroute.Assessmentservice.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping(value = "/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz)
    {
        Quiz quiz1 = this.quizService.addQuiz(quiz);
        return ResponseEntity.ok(quiz1);
    }

    @GetMapping("/")
    public ResponseEntity<Quiz> getQuiz()
    {
        List<Quiz> quiz1 = this.quizService.getQuizzes();
        return (ResponseEntity<Quiz>) ResponseEntity.ok();
    }

//    @GetMapping("/category/{cid}")
//    public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") int cid) {
//        Category category = new Category();
//        category.setCid(cid);
//        return this.quizService.getQuizzesOfCategory(category);
//    }
}
