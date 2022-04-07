package com.stackroute.postservice.repository;

import com.stackroute.postservice.model.PostBlog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostBlogRepository extends MongoRepository<PostBlog,String> {
}
