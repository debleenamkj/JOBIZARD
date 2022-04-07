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
public class PostImage {

    @Id
    private String postId;
    private byte[] postImage;
    private User user;
    private int like;
    private List<Review> reviews;

}
