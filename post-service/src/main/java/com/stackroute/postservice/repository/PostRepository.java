package com.stackroute.postservice.repository;

import com.stackroute.postservice.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post,String> {
}
