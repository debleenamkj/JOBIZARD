package com.stackroute.resourcesservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Indexed(unique = true)
    private int reviewId;
    private User user;
    private String prosMessage;
    private String consMessage;
    private Date reviewDate;

    enum Ratings {
        POOR,
        NOT_BAD,
        GOOD,
        VERY_GOOD,
        EXCELLENT
    }
}
