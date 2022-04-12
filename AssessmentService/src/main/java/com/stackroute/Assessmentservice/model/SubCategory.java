package com.stackroute.Assessmentservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory {
    private int subCategoryID;
//    private String subCategoryTitle;
    private List<Quiz> quizList;

}
