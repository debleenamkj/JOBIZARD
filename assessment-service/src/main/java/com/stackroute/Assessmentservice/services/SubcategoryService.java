package com.stackroute.Assessmentservice.services;

import com.stackroute.Assessmentservice.Exception.SubCategoryAlreadyExistException;
import com.stackroute.Assessmentservice.model.Category;
import com.stackroute.Assessmentservice.model.SubCategory;

import java.util.List;

public interface SubcategoryService {

    public SubCategory addCategory(SubCategory subCategory) throws SubCategoryAlreadyExistException;
    public SubCategory updateCategory(SubCategory subCategory);
    public void deleteSubCategory(int SubCategoryID);
}
