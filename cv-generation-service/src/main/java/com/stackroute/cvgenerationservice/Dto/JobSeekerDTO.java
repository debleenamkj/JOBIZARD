package com.stackroute.cvgenerationservice.Dto;

import com.stackroute.cvgenerationservice.domain.Education;
import com.stackroute.cvgenerationservice.domain.Experience;
import lombok.Data;

import java.util.List;

@Data
public class JobSeekerDTO {


    private String firstName;
    private String lastName;
    private String emailId;
    private long mobileNumber;
    private String designation;
    private  Address address;
    private String careerObjective;
    private List<Experience> experience;
    private List<Education> educationDetails;
    private String[] project;
    private String[] skills;
    private String[] languagesKnown;
    private byte[] jobSeekerImage;
}
