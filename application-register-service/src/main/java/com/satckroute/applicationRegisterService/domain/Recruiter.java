package com.satckroute.applicationRegisterService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import java.util.Arrays;
import java.util.List;


@Document
@Data
// getter & setter / toString()
@AllArgsConstructor
// parameterised Constructor
@NoArgsConstructor
// Default Constructor
public class Recruiter
{
//    @Id
////    @GeneratedValue(strategy = GenerationType.AUTO)
//    private String recruiterId;
//@Column(unique = true)

    @Id
    private String emailId;

//    ------------------------ Not required -----------------
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String mobileNumber;
    private Address addressDetails;
    private OrganizationDetails organizationDetails;
    private String [] languagesKnown;

    @Lob
    private byte[] recruiterImage;

//--------------This part is needed------------
    private String password;
    public Role role;
    private String companyName;
    private String companyUrl;
    private String industryType;
    private byte[] logo;
    private String educationRequired;
    private List<String> skillsRequired;
    private List<JobSeeker> selectedJobSeekers;
}
