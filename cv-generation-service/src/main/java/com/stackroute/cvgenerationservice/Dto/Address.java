package com.stackroute.cvgenerationservice.Dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Address
{
    private String state;
    private String city;
    private int pinCode;
    private String nationality;
}
