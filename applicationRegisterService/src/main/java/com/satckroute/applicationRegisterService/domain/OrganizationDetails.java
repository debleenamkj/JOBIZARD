package com.satckroute.applicationRegisterService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Id
    private String organizationID;

    private String organizationName;
    private String organizationSector;
    private String organizationOrigin;
    private String roleInHiring;
    private Address organizationAddress;

    @Lob
    private byte[] organizationLogo;
}
