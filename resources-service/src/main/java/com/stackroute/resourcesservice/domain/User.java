package com.stackroute.resourcesservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
public class User {
    @Indexed(unique = true)
    private int userId;
    private int email;
    private String firstName;
    private String middleName;
    private String lastName;
    private WorkDetails workDetails;
}
