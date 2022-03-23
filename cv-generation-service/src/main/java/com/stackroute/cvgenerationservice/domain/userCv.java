package com.stackroute.cvgenerationservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class userCv {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String links;
    private String[] careerObjective;
    private String[] education;
    private String[] experience;
    private String[] certifications;
    private String[] project;
    private String[] skills;
    private String[] achievements;
    private String[] personalProfile;

}

