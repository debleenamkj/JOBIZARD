package com.stackroute.recruitmentservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class JobDetails {

    private String jobId;
    private String experienceRequired;
    private String educationRequired;
    private List<String> skillsRequired;
    private String jobRole;
    private Long salary;
    private String lastDate;
    private String  jobLocation;
    private String jobDescription;

}
