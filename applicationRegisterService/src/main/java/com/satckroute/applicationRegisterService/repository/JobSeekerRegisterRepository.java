package com.satckroute.applicationRegisterService.repository;

import com.satckroute.applicationRegisterService.domain.JobSeeker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerRegisterRepository extends MongoRepository<JobSeeker , String>
{
    List<JobSeeker> findAllJobSeekerByFirstName(String firstName);

    List<JobSeeker> findJobSeekerByFirstNameAndLastName(String firstName, String lastName);

    List<JobSeeker> findAllJobSeekerByFirstNameAndMiddleNameAndLastName(String firstName , String middleName , String lastName);
}