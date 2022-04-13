package com.satckroute.applicationRegisterService.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import java.util.Arrays;

@Document
@Data
// getter & setter / toString()
@AllArgsConstructor
// parameterised Constructor
@NoArgsConstructor
// Default Constructor
public class OrganizationDetails
{

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Id
//    private String organizationID;

    @Id
    private String emailId;

    private String organizationName;
    private String organizationSector;
//    private String roleOfHiring;
    private String contactNumber;

    private String password;

    private Address organizationAddress;

    @Lob
    private byte[] organizationLogo;
}
