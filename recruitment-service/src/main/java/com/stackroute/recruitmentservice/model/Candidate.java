package com.stackroute.recruitmentservice.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Candidate {

    private String candidateName;
    private String candidatePhoneNumber;
    private String candidateEmail;
    private String experience;
    private List<String> candidateSkills;
    private String candidateBranch;
    private String passOutYear;
    private String percentageOrCgpa;

}
