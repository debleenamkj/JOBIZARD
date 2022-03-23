package com.stackroute.cvgenerationservice.repository;

import com.stackroute.cvgenerationservice.domain.userCv;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface repository extends MongoRepository<userCv,String> {
}
