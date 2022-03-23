package com.stackroute.resourcesservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private int reviewId;
    private String companyName;
    private String prosMessage;
    private String consMessage;
    private Date reviewDate;
    private WorkDetails workDetails;

    enum Ratings {
        POOR,
        NOT_BAD,
        GOOD,
        VERY_GOOD,
        EXCELLENT
    }
}
