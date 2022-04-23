//package com.stackroute.Assessmentservice.service;
//
//import com.stackroute.Assessmentservice.Exception.CategoryAlreadyExistsException;
//import com.stackroute.Assessmentservice.Exception.CategoryNotExistException;
//import com.stackroute.Assessmentservice.model.Category;
//import com.stackroute.Assessmentservice.model.SubCategory;
//import com.stackroute.Assessmentservice.repository.CategoryRepository;
//import com.stackroute.Assessmentservice.services.serviceIMPL.CategoryServiceIMPL;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class CategoryServiceTest {
//    @Mock
//    private CategoryRepository categoryRepository;
//    @InjectMocks
//    private CategoryServiceIMPL categoryService;
//    private Category category1,category2;
//    List<Category> list;
//    @BeforeEach
//    public void setUp(){
//        List<SubCategory> subCategories1 = new ArrayList<SubCategory>();
//        List<SubCategory> subCategories2 = new ArrayList<SubCategory>();
//        category1 = new Category(201,"computer science","java",subCategories1);
//        category2 =new Category(202,"personal development","aptitude",subCategories2);
//        list= Arrays.asList(category1,category2);
//    }
//    @AfterEach
//    public void tearDown()
//    {
//        category1=null;
//        category2 = null;
//    }
//    @Test
//    public void givenCategoryToSaveReturnSavedCategorySuccess() throws CategoryAlreadyExistsException {
//        when(categoryRepository.findById(category1.getCid())).thenReturn(Optional.ofNullable(null));
//        when(categoryRepository.save(any())).thenReturn(category1);
//        assertEquals(category1,categoryService.addCategory(category1));
//        verify(categoryRepository,times(1)).save(any());
//        verify(categoryRepository,times(1)).findById(any());
//
//    }
//
//    @Test
//    public void givenCategoryToSaveReturnCategoryFailure(){
//        when(categoryRepository.findById(category1.getCid())).thenReturn(Optional.ofNullable(category1));
//        assertThrows(CategoryAlreadyExistsException.class,()->categoryService.addCategory(category1));
//        verify(categoryRepository,times(0)).save(any());
//        verify(categoryRepository,times(1)).findById(any());
//    }
//
////    @Test
////    public void givenCategoryToDeleteShouldDeleteSuccess() throws CategoryNotExistException {
////        when(categoryRepository.findById(category1.getCid())).thenReturn(Optional.ofNullable(category1));
////        boolean flag = categoryService.deleteCategory(category1.getCid());
////        assertEquals(true, flag);
////        verify(categoryRepository, times(1)).deleteById(any());
////        verify(categoryRepository, times(1)).findById(any());
////
////    }
//
//}
