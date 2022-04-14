package com.stackroute.cvgenerationservice.repository;

import com.stackroute.cvgenerationservice.domain.userCv;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface repository extends MongoRepository<userCv,Integer> {
}
