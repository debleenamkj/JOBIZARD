package com.satckroute.applicationRegisterService.repository;

import com.satckroute.applicationRegisterService.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDetailsRepository  extends MongoRepository<Address, String>
{
}
