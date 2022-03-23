package com.stackroute.cvgenerationservice.service;

import com.stackroute.cvgenerationservice.domain.userCv;
import com.stackroute.cvgenerationservice.repository.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class cvServiceImpl implements cvService{

    private repository Repository;
    @Autowired
    public cvServiceImpl(repository repository) {
        Repository = repository;
    }

    @Override
    public userCv saveCv(userCv cv) throws Exception {
        cv.setCvId(UUID.randomUUID().toString());
        return Repository.save(cv);
    }

    @Override
    public void deleteCv(String cvId) throws Exception{
        Optional<userCv> cvDb = this.Repository.findById(cvId);
        if(cvDb.isPresent()){
            this.Repository.delete(cvDb.get());
        }
        else {
            throw new Exception("Cv not found");
        }
    }
    @Override
    public userCv updateCv(userCv cv) throws Exception {
        Optional<userCv> cvDb =this.Repository.findById(cv.getCvId());
        if(cvDb.isPresent()) {
            userCv cvUpdate = cvDb.get();
            cvUpdate.setFirstName(cv.getFirstName());
            cvUpdate.setLastName(cv.getLastName());
            cvUpdate.setEmail(cv.getEmail());
            cvUpdate.setPhoneNumber(cv.getPhoneNumber());
            cvUpdate.setLinks(cv.getLinks());
            cvUpdate.setCareerObjective(cv.getCareerObjective());
            cvUpdate.setEducation(cv.getEducation());
            cvUpdate.setExperience(cv.getExperience());
            cvUpdate.setCertifications(cv.getCertifications());
            cvUpdate.setProject(cv.getProject());
            cvUpdate.setSkills(cv.getSkills());
            cvUpdate.setAchievements(cv.getAchievements());
            cvUpdate.setPersonalProfile(cv.getPersonalProfile());
            Repository.save(cvUpdate);
            return cvUpdate;
        }else{
            throw new Exception("Record not found with given id");
        }
    }

    @Override
    public userCv findCvByCvId(String cvId) throws Exception {
        userCv cv=Repository.findById(cvId).get();
        return cv;
    }
}
