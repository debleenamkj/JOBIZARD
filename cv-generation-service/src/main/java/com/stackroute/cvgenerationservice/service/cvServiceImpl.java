package com.stackroute.cvgenerationservice.service;

import com.stackroute.cvgenerationservice.domain.userCv;
import com.stackroute.cvgenerationservice.exception.CvAlreadyExistsException;
import com.stackroute.cvgenerationservice.exception.CvNotFoundException;
import com.stackroute.cvgenerationservice.repository.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public userCv saveCv(userCv cv, MultipartFile file) throws CvAlreadyExistsException, IOException{
        cv.setCvId(UUID.randomUUID().toString());
        cv.setPicture(file.getBytes());
        if(Repository.findById(cv.getCvId()).isPresent()){
            throw new CvAlreadyExistsException();
        }
        return Repository.save(cv);
    }

    @Override
    public void deleteCv(String cvId) throws CvNotFoundException {
        Optional<userCv> cvDb = this.Repository.findById(cvId);
        if(cvDb.isPresent()){
            this.Repository.delete(cvDb.get());
        }
        else {
            throw new CvNotFoundException();
        }
    }

    @Override
    public userCv updateCv(userCv cv) throws CvNotFoundException {
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
            throw new CvNotFoundException();
        }
    }

    @Override
    public userCv findCvByCvId(String cvId) throws CvNotFoundException {
        userCv cv=Repository.findById(cvId).get();
        return cv;
    }
}
