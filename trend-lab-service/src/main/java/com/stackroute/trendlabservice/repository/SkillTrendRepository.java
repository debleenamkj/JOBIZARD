package com.stackroute.trendlabservice.repository;

import com.stackroute.trendlabservice.model.SkillTrend;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillTrendRepository extends MongoRepository<SkillTrend,Long> {

}
