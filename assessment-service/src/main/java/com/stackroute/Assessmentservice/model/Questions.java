package com.stackroute.Assessmentservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Questions {
    private int quesNo;
    private String question;
    private String a;
    private String b;
    private String c;
    private String d;

}
