package com.stackroute.Assessmentservice.repository;

import com.stackroute.Assessmentservice.model.Category;
import com.stackroute.Assessmentservice.model.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category,Integer> {
    Category findBySubCategoryTitle(String subcategoryTitle);
}
