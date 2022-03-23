package com.stackroute.cvgenerationservice.service;

import com.stackroute.cvgenerationservice.domain.userCv;
import com.stackroute.cvgenerationservice.repository.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cvServiceImpl implements cvService{

    private repository Repository;
    @Autowired
    public cvServiceImpl(repository repository) {
        Repository = repository;
    }

    @Override
    public userCv saveCv(userCv cv) throws Exception {
        if(Repository.findById(cv.getId()).isPresent())
        {
            throw new Exception();
        }
        return Repository.save(cv);
    }

    @Override
    public boolean deleteCv(userCv cv) {
        return false;
    }
}
