package com.stackroute.cvgenerationservice.service;

import com.stackroute.cvgenerationservice.domain.userCv;

public interface cvService {
    userCv saveCv(userCv cv) throws Exception;
    public void deleteCv(String cvId) throws Exception;
    userCv updateCv(userCv cv) throws Exception;
    userCv findCvByCvId(String cvId) throws Exception;
}
