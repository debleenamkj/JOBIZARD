package com.satckroute.applicationRegisterService.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
// getter & setter / toString()
@AllArgsConstructor
// parameterised Constructor
@NoArgsConstructor
// Default Constructor
public class Address
{
    @Id
    private String state;
    private String city;
    private int pinCode;
    private String nationality;
}
