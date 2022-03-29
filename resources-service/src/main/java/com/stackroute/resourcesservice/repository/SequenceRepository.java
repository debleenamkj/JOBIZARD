package com.stackroute.resourcesservice.repository;

import com.stackroute.resourcesservice.domain.Sequence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends MongoRepository<Sequence, String> {
}
