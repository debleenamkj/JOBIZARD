package com.satckroute.applicationRegisterService.rabbitMQ;

import com.satckroute.applicationRegisterService.domain.Address;
import com.satckroute.applicationRegisterService.domain.Details;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.util.Date;
import java.util.List;

@Data
// getter & setter / toString()
@AllArgsConstructor
// parameterised Constructor
@NoArgsConstructor
// Default Constructor
public class JobSeekerDTO
{
    private String emailId;
    private String firstName;
    private String lastName;

    private String gender;
    private Date dateOfBirth;
    private long mobileNumber;
    private String password;

//    private String role;
//    private Role Role.JOBSEEKER;

    private Address address;
    private List educationDetails; //list
    private String[] languagesKnown;
    private Details additionalDetails;

    @Lob
    private byte[] jobSeekerImage;
}
