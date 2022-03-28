package com.stackroute.trendlabservice.service;

import com.stackroute.trendlabservice.exception.SkillTrendAlreadyExistsException;
import com.stackroute.trendlabservice.exception.SkillTrendNotFoundException;
import com.stackroute.trendlabservice.model.SkillTrend;
import com.stackroute.trendlabservice.repository.SkillTrendRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SkillTrendServiceImpl implements SkillTrendService{

    private SkillTrendRepository skillTrendRepository;

    @Autowired
    public SkillTrendServiceImpl(SkillTrendRepository skillTrendRepository) {
        log.info("Autowiring SkillTrendRepository Done");
        this.skillTrendRepository = skillTrendRepository;
    }

    @Override
    public SkillTrend saveSkill(SkillTrend skillTrend) {
        log.debug("Inside SkillTrendServiceImpl - saveSkill");
        try {
            if (!(skillTrendRepository.findById(skillTrend.getSkillId()).isEmpty())) {
                log.error("Inside SkillTrendServiceImpl - saveSkill - SkillTrendAlreadyExistsException");
                throw new SkillTrendAlreadyExistsException();
            }
        }
        catch (SkillTrendAlreadyExistsException e){
            e.getMessage();
            log.error("In saveSkill of SkillTrendServiceImpl"+e);
        }
        catch (Exception e){
            log.error("In saveSkill of SkillTrendServiceImpl"+e);
            e.printStackTrace();
        }
        return skillTrendRepository.save(skillTrend);
    }

    @Override
    public SkillTrend updateSkill(SkillTrend skillTrend) {
        log.debug("Inside SkillTrendServiceImpl - updateSkill");
        try {
            if (skillTrendRepository.findById(skillTrend.getSkillId()).isEmpty()) {
                log.error("Inside SkillTrendServiceImpl - saveSkill - SkillTrendNotFoundException");
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
    public SkillTrend deleteSkill(Long skillId){
        log.debug("Inside SkillTrendServiceImpl - deleteSkill");
        SkillTrend skillTrend = skillTrendRepository.findById(skillId).get();
        try {
            if (skillTrendRepository.findById(skillId).isEmpty()) {
                log.error("Inside SkillTrendServiceImpl - saveSkill - SkillTrendNotFoundException");
                throw new SkillTrendNotFoundException();
            }
            skillTrendRepository.delete(skillTrend);
        }
        catch (SkillTrendNotFoundException se){
            se.getMessage();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return skillTrend;
    }

    @Override
    public List<SkillTrend> getAllSkills(){
        log.debug("Inside SkillTrendServiceImpl - getAllSkills");
        return skillTrendRepository.findAll();
    }

}
