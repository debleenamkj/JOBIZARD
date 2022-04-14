package com.stackroute.resourcesservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
//@AllArgsConstructor
public class Sequence {

    @Id
    private String id;
    private int sequenceNumber;
}
