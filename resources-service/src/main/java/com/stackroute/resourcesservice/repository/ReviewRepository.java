package com.stackroute.resourcesservice.repository;

import com.stackroute.resourcesservice.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Company, Integer> {
    public Company findByCompanyName(String companyName);
}
