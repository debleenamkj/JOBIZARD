package com.stackroute.controller;

import com.stackroute.model.exam.Quiz;
import com.stackroute.repo.CategoryRepository;
import com.stackroute.repo.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/{keyword}")
    public List<Quiz> search(@PathVariable ("keyword") String keyword,Principal principal){

        List<Quiz> quizzes = this.quizRepository.findbycategory(keyword);
        return quizzes;
    }




}
