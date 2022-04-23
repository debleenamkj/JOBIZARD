package com.satckroute.applicationRegisterService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Document
@Data
// getter & setter / toString()
@AllArgsConstructor
// parameterised Constructor
@NoArgsConstructor
// Default Constructor
public class JobSeeker
{
//    @Transient
//    public static final String SEQUENCE_NAME="user_sequence";

   // private String jobSeekerId;

//    @Column(unique = true)
    @Id
        private String emailId;
    private String firstName;
    private String lastName;

    private String gender;
    private Date dateOfBirth;
    private String mobileNumber;
    private String password;
    private JobSeekerProgress seekerProgress;
    private int progress;
    public Role role;
    public char[] experience;
//    private Role Role.JOBSEEKER;

    private Address address;
    private List<Education> educationDetails; //list
    private String[] languagesKnown;
    private Details additionalDetails;
    private char[] objective;

    @Lob
    private byte[] jobSeekerImage;


    // custom Constructor for test cases

    public JobSeeker(String emailId, String firstName, String lastName, String gender, Date dateOfBirth, String mobileNumber,
                     String password,  Address address, Details additionalDetails) {
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.password = password;
//        this.role = role;
        this.address = address;
        this.additionalDetails = additionalDetails;
    }
}




