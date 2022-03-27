package com.stackroute.trendlabservice.service;

import com.stackroute.trendlabservice.model.SkillTrend;

import java.util.List;

public interface SkillTrendService {

    SkillTrend saveSkill(SkillTrend skillTrend);
    SkillTrend updateSkill(SkillTrend skillTrend);
    Boolean deleteSkill(Long skillId);
    List<SkillTrend> getAllSkills();
    Boolean deleteEverything();
}
