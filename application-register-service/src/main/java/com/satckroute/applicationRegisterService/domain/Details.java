package com.satckroute.applicationRegisterService.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document
@Data
// getter & setter / toString()
@AllArgsConstructor
// parameterised Constructor
@NoArgsConstructor
// Default Constructor
public class Details
{
    private String[] academicsCertification;
    private Skill[] skillSet;
    private String[] jobPreferences;
    private String[] achievements;
}
