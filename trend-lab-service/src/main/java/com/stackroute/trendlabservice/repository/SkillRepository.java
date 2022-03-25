package com.stackroute.trendlabservice.repository;

import com.stackroute.trendlabservice.model.SkillTrend;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillRepository extends MongoRepository<SkillTrend,Long> {
}
