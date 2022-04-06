package com.stackroute.postservice.repository;

import com.stackroute.postservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
