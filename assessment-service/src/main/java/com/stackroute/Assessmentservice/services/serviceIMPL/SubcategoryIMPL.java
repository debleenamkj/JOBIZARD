package com.stackroute.Assessmentservice.services.serviceIMPL;

import com.stackroute.Assessmentservice.Exception.SubCategoryAlreadyExistException;
import com.stackroute.Assessmentservice.model.SubCategory;
import com.stackroute.Assessmentservice.repository.SubcategoryRepository;
import com.stackroute.Assessmentservice.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubcategoryIMPL implements SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Override
    public SubCategory addCategory(SubCategory subCategory) throws SubCategoryAlreadyExistException {
        if(subcategoryRepository.findById(subCategory.getSubCategoryID()).isPresent()){

            throw new SubCategoryAlreadyExistException();
        }
        else
            return this.subcategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory updateCategory(SubCategory subCategory) {
        return null;
    }

    @Override
    public void deleteSubCategory(int SubCategoryID) {

    }
}
