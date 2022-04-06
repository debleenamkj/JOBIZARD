package com.stackroute.postservice.repository;

import com.stackroute.postservice.model.PostImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostImageRepository extends MongoRepository<PostImage,String> {
}
