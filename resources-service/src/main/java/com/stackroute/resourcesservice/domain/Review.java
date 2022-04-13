package com.stackroute.resourcesservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
public class Review {
    @Transient
    public static final String sequenceName = "review_id_generation";

    @Id
    private int reviewId;
    private User user;
    private String prosMessage;
    private String consMessage;
    private Date reviewDate;
    private Ratings companyRatings;
}
