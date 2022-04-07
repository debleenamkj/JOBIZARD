package com.satckroute.applicationRegisterService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;

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
    private String middleName;
    private String lastName;

    private String gender;
    private String dateOfBirth;
    private long mobileNumber;
    private String password;

//    private String role;
//    private Role Role.JOBSEEKER;

    private Address address;
    private Education educationDetails;
    private String[] languagesKnown;
    private Details additionalDetails;

    @Lob
    private byte[] jobSeekerImage;

}




