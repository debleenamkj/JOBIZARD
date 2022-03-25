package com.stackroute.trendlabservice.service;

import com.stackroute.trendlabservice.model.SkillTrend;
import com.stackroute.trendlabservice.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SkillTrendServiceImpl {

    @Autowired
    SkillRepository skillRepository;

    public SkillTrend saveSkills(SkillTrend skillTrend){
        return skillRepository.save(skillTrend);
    }
}
