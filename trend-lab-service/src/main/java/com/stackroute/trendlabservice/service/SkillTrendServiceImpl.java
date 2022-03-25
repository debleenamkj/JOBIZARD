package com.stackroute.trendlabservice.service;

import com.stackroute.trendlabservice.exception.SkillTrendAlreadyExistsException;
import com.stackroute.trendlabservice.exception.SkillTrendNotFoundException;
import com.stackroute.trendlabservice.model.SkillTrend;
import com.stackroute.trendlabservice.repository.SkillTrendRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SkillTrendServiceImpl implements SkillTrendService{

    @Autowired
    SkillTrendRepository skillTrendRepository;

    @Override
    public SkillTrend saveSkill(SkillTrend skillTrend) {
        try {
            if (!(skillTrendRepository.findById(skillTrend.getSkillId()).isEmpty())) {
                throw new SkillTrendAlreadyExistsException();
            }
        }
        catch (SkillTrendAlreadyExistsException e){
            e.getMessage();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return skillTrendRepository.save(skillTrend);
    }

    @Override
    public SkillTrend updateSkill(SkillTrend skillTrend) {
        try {
            if (skillTrendRepository.findById(skillTrend.getSkillId()).isEmpty()) {
                throw new SkillTrendNotFoundException();
            }
        }
        catch (SkillTrendNotFoundException se){
            se.getMessage();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return skillTrendRepository.save(skillTrend);
    }

    @Override
    public Boolean deleteSkill(Long skillId){
        try {
            if (skillTrendRepository.findById(skillId).isEmpty()) {
                throw new SkillTrendNotFoundException();
            }
            SkillTrend skillTrend = skillTrendRepository.findById(skillId).get();
            skillTrendRepository.delete(skillTrend);
        }
        catch (SkillTrendNotFoundException se){
            se.getMessage();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<SkillTrend> getAllSkills(){
        return skillTrendRepository.findAll();
    }
}
