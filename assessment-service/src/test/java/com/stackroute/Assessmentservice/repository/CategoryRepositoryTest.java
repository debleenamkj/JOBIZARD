//package com.stackroute.Assessmentservice.repository;
//
//import com.stackroute.Assessmentservice.model.Category;
//import com.stackroute.Assessmentservice.model.SubCategory;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@ExtendWith(SpringExtension.class)
//@DataMongoTest
//public class CategoryRepositoryTest {
//    @Autowired
//    private CategoryRepository categoryRepository;
//    private Category category;
//
//    @BeforeEach
//    public void setUp() {
//        List<SubCategory> subCategories = new ArrayList<SubCategory>();
////        subCategories.add();
//        category = new Category(103, "JAVA", "Computer Science", subCategories);
//    }
//
//    @AfterEach
//    public void tearDown() {
//
//        category = null;
//        categoryRepository.deleteAll();
//    }
//
////    @Test
////    public void givenCategoryToSaveShouldReturnCategory() {
////        categoryRepository.insert(category);
////        Category category1 = categoryRepository.findById(category.getCid()).get();
////        assertNotNull(category1);
////        assertEquals(category.getCid(), category1.getCid());
////    }
//
////    @Test
////    public void givenCategoryToDeleteReturnCategory()
////    {
////        categoryRepository.insert(category);
////        Category category1 = categoryRepository.findById(category.getCid()).get();
////
////        categoryRepository.delete(category1);
////        assertEquals(Optional.empty(),categoryRepository.findById(category1.getCid()));
////    }
//
//    @Test
//    public void givenCategoryReturnAllTrack()
//    {
//        categoryRepository.insert(category);
//        List<SubCategory> subCategories1 = new ArrayList<SubCategory>();
//        Category category1 =new Category(103,"computer science","java",subCategories1);
//        categoryRepository.insert(category1);
//        List<Category> list = categoryRepository.findAll();
//        assertEquals("computer science",list.get(1).getCategoryTitle());
//        assertEquals(103,list.get(0).getCid());
//        assertEquals("java",list.get(1).getSubCategoryTitle());
//
//    }
//}
