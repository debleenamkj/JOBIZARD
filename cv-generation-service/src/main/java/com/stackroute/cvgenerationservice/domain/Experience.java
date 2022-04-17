package com.stackroute.cvgenerationservice.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Experience {
    private String jobPosition;
    private String companyName;
    private String duration;
    private String location;
    private String workDescription;
}
