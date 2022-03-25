package com.stackroute.cvgenerationservice.service;

import com.stackroute.cvgenerationservice.domain.userCv;
import com.stackroute.cvgenerationservice.exception.CvAlreadyExistsException;
import com.stackroute.cvgenerationservice.exception.CvNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface cvService {
    userCv saveCv(userCv cv, MultipartFile file) throws CvAlreadyExistsException, IOException, CvNotFoundException;
    public void deleteCv(String cvId) throws CvNotFoundException;
    userCv updateCv(userCv cv) throws CvNotFoundException;
    userCv findCvByCvId(String cvId) throws CvNotFoundException;
}
