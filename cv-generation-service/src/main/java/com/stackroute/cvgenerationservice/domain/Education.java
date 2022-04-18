package com.stackroute.cvgenerationservice.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Education {
    private String typeOfdegree;
    private String collegeName;
    private String duration;
    private String description;
}
