package com.stackroute.model;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EmailRequest {
    private String to;
    private String subject;
    private String message;
    private String companyName;
}

