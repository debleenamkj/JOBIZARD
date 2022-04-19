package com.stackroute.Assessmentservice.services.serviceIMPL;

import com.stackroute.Assessmentservice.Exception.CategoryAlreadyExistsException;
import com.stackroute.Assessmentservice.Exception.SubCategoryNotExistsException;
import com.stackroute.Assessmentservice.model.Category;
import com.stackroute.Assessmentservice.model.Quiz;
import com.stackroute.Assessmentservice.model.SubCategory;
import com.stackroute.Assessmentservice.repository.CategoryRepository;
import com.stackroute.Assessmentservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class CategoryServiceIMPL implements CategoryService {
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
    public boolean deleteCategory(int categoryId) {
        Category category = new Category();
        if (category == null) {
            return  false;
        }
        category.setCid(categoryId);
        this.categoryRepository.delete(category);
        return true;
    }

    @Override
    public Quiz getQuiz(String subcategoryTitle)throws SubCategoryNotExistsException {
        Category category = this.categoryRepository.findBySubCategoryTitle(subcategoryTitle);
        if(category==null)
        {
            throw new SubCategoryNotExistsException();
        }
        else {
            Random random = new Random();
            int quizzes = category.getSubCategory().size();
//            System.out.println(quizzes);
            int index = random.nextInt(quizzes);
            List<Quiz> quiz = category.getSubCategory().get(index).getQuizList();
            return quiz.get(0);
        }
    }
}
