package com.satckroute.applicationRegisterService.repository;

import com.satckroute.applicationRegisterService.domain.JobSeeker;
import com.satckroute.applicationRegisterService.domain.Recruiter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruiterRegisterRepository extends MongoRepository<Recruiter , String>
{
    List<Recruiter> findAllRecruiterByFirstName(String firstName);
}




