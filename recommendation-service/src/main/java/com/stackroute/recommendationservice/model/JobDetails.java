package com.stackroute.recommendationservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@NodeEntity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobDetails {
    @Id
    private String emailId;
    private ArrayList skillsRequired;
    private String education;
}
