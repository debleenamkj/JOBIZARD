package com.stackroute.postservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class PostBlog {
    @Id
    private String postId;
    private User user;
    private String postBlog;
    private int like;
    private List<Review> reviews;
}
