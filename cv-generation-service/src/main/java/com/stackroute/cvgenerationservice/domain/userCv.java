package com.stackroute.cvgenerationservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Lob;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class userCv {
    @Id
    private String cvId;
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private String links;
    private String[] careerObjective;
    private String[] education;
    private String[] experience;
    private String[] certifications;
    private String[] project;
    private String[] skills;
    private String[] achievements;
    private String[] personalProfile;
    @Lob
    private byte[] picture;
}

