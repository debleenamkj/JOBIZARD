package com.stackroute.cvgenerationservice.service;

import com.stackroute.cvgenerationservice.domain.sequence;
import com.stackroute.cvgenerationservice.domain.userCv;
import com.stackroute.cvgenerationservice.exception.CvAlreadyExistsException;
import com.stackroute.cvgenerationservice.exception.CvNotFoundException;
import com.stackroute.cvgenerationservice.repository.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class cvServiceImpl implements cvService{

    private MongoOperations mongoOperations;
    private repository Repository;

    @Autowired
    public cvServiceImpl(MongoOperations mongoOperations, repository repository) {
        this.mongoOperations = mongoOperations;
        Repository = repository;
    }

    @Override
    public int getSequenceNumber(String sequenceName) {
        //get sequence no
        Query query = new Query(Criteria.where("_id").is(sequenceName));
        //update the sequence no
        Update update = new Update().inc("seqNo", 1);
        //modify in document
        sequence counter = mongoOperations
                .findAndModify(query,
                        update, options().returnNew(true).upsert(true),
                        sequence.class);

        return !Objects.isNull(counter) ? counter.getSeqNo() : 1;
    }

    @Override
    public userCv saveCv(userCv cv, MultipartFile file) throws CvAlreadyExistsException, IOException{
        cv.setCvId(getSequenceNumber(userCv.SEQUENCE_NAME));
        cv.setPicture(file.getBytes());
        if(Repository.findById(cv.getCvId()).isPresent()){
            throw new CvAlreadyExistsException();
        }
        return Repository.save(cv);
    }

    @Override
    public void deleteCv(int cvId) throws CvNotFoundException {
        Optional<userCv> cvDb = this.Repository.findById(cvId);
        if(cvDb.isPresent()){
            this.Repository.delete(cvDb.get());
        }
        else {
            throw new CvNotFoundException();
        }
    }

    @Override
    public userCv updateCv(userCv cv,MultipartFile file) throws CvNotFoundException, IOException {
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
            cvUpdate.setPicture(file.getBytes());
            Repository.save(cvUpdate);
            return cvUpdate;
        }else{
            throw new CvNotFoundException();
        }
    }

    @Override
    public userCv findCvByCvId(int cvId) throws CvNotFoundException {
        userCv cv=Repository.findById(cvId).get();
        return cv;
    }
}
