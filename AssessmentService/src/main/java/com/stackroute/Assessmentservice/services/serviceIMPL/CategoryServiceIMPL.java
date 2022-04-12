package com.stackroute.Assessmentservice.services.serviceIMPL;

import com.stackroute.Assessmentservice.Exception.CategoryAlreadyExistsException;
import com.stackroute.Assessmentservice.model.Category;
import com.stackroute.Assessmentservice.model.Quiz;
import com.stackroute.Assessmentservice.repository.CategoryRepository;
import com.stackroute.Assessmentservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) throws CategoryAlreadyExistsException {
        if(categoryRepository.findById(category.getCid()).isPresent()){

            throw new CategoryAlreadyExistsException();
        }
        else
            return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = new Category();
        category.setCid(categoryId);
        this.categoryRepository.delete(category);

    }

    @Override
    public List<Quiz> getQuiz(String subcategoryTitle) {
       Category category = this.categoryRepository.findBySubCategoryTitle(subcategoryTitle);
       Random random = new Random();
        int index=random.nextInt(2);
        List<Quiz> quiz =category.getSubCategory().get(index).getQuizList();
        return quiz;
    }
}
