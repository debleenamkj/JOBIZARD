package com.stackroute.trendlabservice.service;

import com.stackroute.trendlabservice.model.SkillTrend;

import java.util.List;

public interface SkillTrendService {

    SkillTrend saveSkill(SkillTrend skillTrend);
    SkillTrend updateSkill(SkillTrend skillTrend);
    SkillTrend deleteSkill(Long skillId);
    List<SkillTrend> getAllSkills();
    List<SkillTrend> getNameOfSkills();

}
