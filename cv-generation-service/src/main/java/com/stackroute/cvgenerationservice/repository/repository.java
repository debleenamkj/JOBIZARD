package com.stackroute.cvgenerationservice.repository;

import com.stackroute.cvgenerationservice.domain.JobSeeker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface repository extends MongoRepository<JobSeeker,Integer> {


    JobSeeker findByEmail(String email);
}
