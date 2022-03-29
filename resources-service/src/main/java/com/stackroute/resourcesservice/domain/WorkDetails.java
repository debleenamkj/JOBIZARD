package com.stackroute.resourcesservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkDetails {
    private boolean currentlyWorking;
    private String jobRole;
    private String yearsOfExperience;
    private int workHoursPerDay;
    private int workDaysPerWeek;
}
