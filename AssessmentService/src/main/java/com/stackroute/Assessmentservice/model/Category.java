package com.stackroute.Assessmentservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private int cid;
    private String categoryTitle;
    private String subCategoryTitle;
    private List<SubCategory> subCategory;
}
