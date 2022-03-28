package com.satckroute.applicationRegisterService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;


@Document
@Data
// getter & setter / toString()
@AllArgsConstructor
// parameterised Constructor
@NoArgsConstructor
// Default Constructor
//@Table(name="jobseeker")
public class JobSeeker
{

//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
//    @SequenceGenerator(name="sequence",sequenceName ="jobseeker" , allocationSize = 30)

//    @Indexed(unique = true)
    @Id
    private String jobSeekerId;

//    @Column(unique = true)
    private String emailId;

    private String firstName;
    private String middleName;
    private String lastName;

    private String gender;
    private String dateOfBirth;
    private long mobileNumber;
    private String password;

    private Role jobSeeker;

    private Address address;
    private Education educationDetails;
    private String[] languagesKnown;
    private Details additionalDetails;

    @Lob
    private byte[] jobSeekerImage;

}




