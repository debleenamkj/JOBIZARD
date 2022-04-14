package com.stackroute.Assessmentservice.services;

import com.stackroute.Assessmentservice.Exception.CategoryAlreadyExistsException;
import com.stackroute.Assessmentservice.Exception.SubCategoryNotExistsException;
import com.stackroute.Assessmentservice.model.Category;
import com.stackroute.Assessmentservice.model.Quiz;

import java.util.List;


public interface CategoryService {

    public Category addCategory(Category category) throws CategoryAlreadyExistsException;
    public Category updateCategory(Category category);
    public boolean deleteCategory(int categoryId);
    public Quiz getQuiz(String subCategoryTitle) throws SubCategoryNotExistsException;
}