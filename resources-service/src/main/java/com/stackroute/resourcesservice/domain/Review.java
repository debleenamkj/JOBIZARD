package com.stackroute.resourcesservice.domain;

import java.util.Date;

public class Review {
    private int reviewId;
    private String companyName;
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
