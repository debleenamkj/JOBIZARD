package com.stackroute.cvgenerationservice.service;

import com.stackroute.cvgenerationservice.domain.sequence;
import com.stackroute.cvgenerationservice.domain.JobSeeker;
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
        Query query = new Query(Criteria.where("_id").is(sequenceName));
        Update update = new Update().inc("seqNo", 1);
        sequence counter = mongoOperations
                .findAndModify(query,
                        update, options().returnNew(true).upsert(true),
                        sequence.class);
        return !Objects.isNull(counter) ? counter.getSeqNo() : 1;
    }

    @Override
    public JobSeeker saveCv(JobSeeker cv, MultipartFile file) throws CvAlreadyExistsException, IOException{
        cv.setCvId(getSequenceNumber(JobSeeker.SEQUENCE_NAME));
        cv.setPicture(file.getBytes());
        if(Repository.findById(cv.getCvId()).isPresent()){
            throw new CvAlreadyExistsException();
        }
        return Repository.save(cv);
    }

    @Override
    public void deleteCv(int cvId) throws CvNotFoundException {
        Optional<JobSeeker> cvDb = this.Repository.findById(cvId);
        if(cvDb.isPresent()){
            this.Repository.delete(cvDb.get());
        }
        else {
            throw new CvNotFoundException();
        }
    }

    @Override
    public JobSeeker updateCv(int cvId, JobSeeker cv, MultipartFile file) throws CvNotFoundException, IOException {
        JobSeeker existingCv = findCvByCvId(cvId);
        if(existingCv != null) {
            existingCv.setFirstName(cv.getFirstName());
            existingCv.setLastName(cv.getLastName());
            existingCv.setEmail(cv.getEmail());
            existingCv.setPhoneNumber(cv.getPhoneNumber());
            existingCv.setAddress(cv.getAddress());
            existingCv.setCareerObjective(cv.getCareerObjective());
            existingCv.setEducation(cv.getEducation());
            existingCv.setExperience(cv.getExperience());
            existingCv.setProject(cv.getProject());
            existingCv.setSkills(cv.getSkills());
            existingCv.setLanguages(cv.getLanguages());
            existingCv.setPicture(file.getBytes());
            Repository.save(existingCv);
            return existingCv;
        }else{
            throw new CvNotFoundException();
        }
    }

    @Override
    public JobSeeker findCvByCvId(int cvId) throws CvNotFoundException {
        JobSeeker cv=Repository.findById(cvId).get();
        return cv;
    }

    @Override
    public JobSeeker findByEmail(String email) throws CvNotFoundException {
        JobSeeker cv = Repository.findByEmail(email);
        return cv;
    }


}
