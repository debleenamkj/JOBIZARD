package com.satckroute.applicationRegisterService.rabbitMQ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDetails {

    private String emailId;
    private ArrayList skillsRequired;
    private String education;
}
