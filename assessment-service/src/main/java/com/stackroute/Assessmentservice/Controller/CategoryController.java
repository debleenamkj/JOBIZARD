package com.stackroute.Assessmentservice.Controller;

import com.stackroute.Assessmentservice.Exception.CategoryAlreadyExistsException;
import com.stackroute.Assessmentservice.Exception.SubCategoryNotExistsException;
import com.stackroute.Assessmentservice.model.Category;
import com.stackroute.Assessmentservice.model.Quiz;
import com.stackroute.Assessmentservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) throws CategoryAlreadyExistsException {
        System.out.println("Assessment Service post question"+category);
        Category category1 = this.service.addCategory(category);
        return ResponseEntity.ok(category1);
    }


    @PutMapping("/")
    public ResponseEntity<Category> updateCategory(@RequestBody  Category category){
        Category category1 = this.service.updateCategory(category);
        return ResponseEntity.ok(category1);
    }


    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") int categoryId) {
        this.service.deleteCategory(categoryId);
    }

    @GetMapping("/{subCategoryTitle}")
    public Quiz getQuiz(@PathVariable String subCategoryTitle) throws SubCategoryNotExistsException {
        Quiz quizzes = this.service.getQuiz(subCategoryTitle);
        return quizzes;
    }
}

