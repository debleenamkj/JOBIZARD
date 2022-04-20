//package com.stackroute.Assessmentservice.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.Assessmentservice.Controller.CategoryController;
//import com.stackroute.Assessmentservice.Exception.CategoryAlreadyExistsException;
//import com.stackroute.Assessmentservice.model.Category;
//import com.stackroute.Assessmentservice.model.SubCategory;
//import com.stackroute.Assessmentservice.services.CategoryService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.times;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class CategoryControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Mock
//    private CategoryService categoryService;
//    private Category category1,category2;
//    List<Category> list;
//
//    @InjectMocks
//    private CategoryController categoryController;
//    @BeforeEach
//    public void setUp(){
//        List<SubCategory> subCategories1 = new ArrayList<SubCategory>();
//        List<SubCategory> subCategories2 = new ArrayList<SubCategory>();
//        category1 = new Category(201,"computer science","java",subCategories1);
//        category2 =new Category(202,"personal development","aptitude",subCategories2);
//        list= Arrays.asList(category1,category2);
//        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
//    }
//    @AfterEach
//    public void tearDown()
//    {
//        category1=null;
//        category2 = null;
//
//    }
//    @Test
//    public void givenCategoryToSaveReturnSaveCategorySuccess() throws Exception {
//        when(categoryService.addCategory(any())).thenReturn(category1);
//        mockMvc.perform(post("/category/")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(category1)))
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//        verify(categoryService,times(1)).addCategory(any());
//    }
//    @Test
//    public void givenCategoryToSaveReturnSaveCategoryFailure() throws Exception {
//        when(categoryService.addCategory(any())).thenThrow(CategoryAlreadyExistsException.class);
//        mockMvc.perform(post("/category/")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(category1)))
//                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
//        verify(categoryService, times(1)).addCategory(any());
//    }
//
//    private static String jsonToString(final Object ob) throws JsonProcessingException {
//        String result;
//
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonContent = mapper.writeValueAsString(ob);
//            result = jsonContent;
//        } catch(JsonProcessingException e) {
//            result = "JSON processing error";
//        }
//
//        return result;
//    }
//
//
//
//
//
//}
