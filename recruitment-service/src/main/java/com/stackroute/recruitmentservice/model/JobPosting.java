package com.stackroute.recruitmentservice.model;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobPosting {

    private int companyId;
    private String companyName;
    private String companyMail;
    private String industryType;
    List<JobDetails> jobDetailsList;


}
