package com.stackroute.resourcesservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
//@AllArgsConstructor
public class User {
    public boolean anonymousUser;
    private String email;
    private String name;
    private WorkDetails workDetails;
}
