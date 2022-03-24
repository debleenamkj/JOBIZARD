package com.stackroute.cvgenerationservice.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
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

    public userCv() {
    }

    public userCv(String cvId, String firstName, String lastName, String email, long phoneNumber, String links, String[] careerObjective, String[] education, String[] experience, String[] certifications, String[] project, String[] skills, String[] achievements, String[] personalProfile) {
        this.cvId = cvId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.links = links;
        this.careerObjective = careerObjective;
        this.education = education;
        this.experience = experience;
        this.certifications = certifications;
        this.project = project;
        this.skills = skills;
        this.achievements = achievements;
        this.personalProfile = personalProfile;
    }
}

