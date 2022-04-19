package com.satckroute.applicationRegisterService.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document
@Data
// getter & setter / toString()
@AllArgsConstructor
// parameterised Constructor
@NoArgsConstructor
// Default Constructor
public class Education
{
    @Id
    private String courses;
    private String streamOrField;
    private String universityOrInstitute;
    private String courseType;
    private String passingOutYear;
    private float percentageOrCgpa;
}
