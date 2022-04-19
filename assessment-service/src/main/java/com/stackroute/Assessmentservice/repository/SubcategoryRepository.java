package com.stackroute.Assessmentservice.repository;

import com.stackroute.Assessmentservice.model.SubCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubcategoryRepository extends MongoRepository<SubCategory,Integer> {
}
