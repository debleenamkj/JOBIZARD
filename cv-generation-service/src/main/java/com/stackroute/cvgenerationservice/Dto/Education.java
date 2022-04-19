package com.stackroute.cvgenerationservice.Dto;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Education
{
    private String courses;
    private String streamOrField;
    private String universityOrInstitute;
    private String courseType;
    private int passingOutYear;
    private float percentageOrCgpa;
}
