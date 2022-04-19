package com.satckroute.applicationRegisterService.rabbitMQ;

import com.satckroute.applicationRegisterService.domain.Address;
import com.satckroute.applicationRegisterService.domain.Details;
import com.satckroute.applicationRegisterService.domain.Role;
;

import javax.persistence.Lob;
import java.util.Date;
import java.util.List;

public class JobSeekerDTO
{
    private String emailId;
    private String firstName;
    private String lastName;

    private String gender;
    private Date dateOfBirth;
    private String mobileNumber;
    private String password;

    public Role role;

    private Address address;
    private List educationDetails;
    private String[] languagesKnown;
    private Details additionalDetails;

    @Lob
    private byte[] jobSeekerImage;
}
