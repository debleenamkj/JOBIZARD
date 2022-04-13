package com.stackroute.resourcesservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "suggestions")
public class Suggestion {

    @Id
    private int suggestionId;
    private String category;
    private String skillType;
    private String sourceUrl;
}
