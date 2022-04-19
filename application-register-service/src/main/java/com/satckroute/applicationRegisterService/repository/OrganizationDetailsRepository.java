package com.satckroute.applicationRegisterService.repository;

import com.satckroute.applicationRegisterService.domain.OrganizationDetails;
import com.satckroute.applicationRegisterService.domain.Recruiter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationDetailsRepository extends MongoRepository<OrganizationDetails, String >
{
    List<OrganizationDetails> findAllOrganizationByOrganizationName(String organizationName);
}
