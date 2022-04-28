package com.stackroute.Assessmentservice.Controller;

import com.stackroute.Assessmentservice.Exception.SubCategoryAlreadyExistException;
import com.stackroute.Assessmentservice.model.SubCategory;
import com.stackroute.Assessmentservice.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/subcategory")
public class SubcategoryController {

    @Autowired
    private SubcategoryService service;
    @PostMapping
     public ResponseEntity<SubCategory> addSubCategory(@RequestBody SubCategory subCategory) throws SubCategoryAlreadyExistException
    {

        SubCategory subCategory1 = this.service.addCategory(subCategory);
        return ResponseEntity.ok(subCategory1);
    }

    @PutMapping("/")
    public ResponseEntity<SubCategory> updateCategory(@RequestBody SubCategory subCategory){
        SubCategory subCategory1 = this.service.updateCategory(subCategory);
        return ResponseEntity.ok(subCategory1);
    }


    @DeleteMapping("/{SubcategoryId}")
    public void deleteCategory(@PathVariable("SubcategoryId") int SubID) {
        this.service.deleteSubCategory(SubID);
    }

}
