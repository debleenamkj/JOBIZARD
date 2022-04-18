package com.stackroute.resourcesservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
public class WorkDetails {
    private boolean currentlyWorking;
    private String jobRole;
    private String yearsOfExperience;
}
