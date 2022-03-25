package com.stackroute.cvgenerationservice.service;

import com.stackroute.cvgenerationservice.domain.userCv;
import com.stackroute.cvgenerationservice.exception.CvAlreadyExistsException;
import com.stackroute.cvgenerationservice.exception.CvNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface cvService {
    public int getSequenceNumber(String sequenceName);
    userCv saveCv(userCv cv, MultipartFile file) throws CvAlreadyExistsException, IOException, CvNotFoundException;
    public void deleteCv(int cvId) throws CvNotFoundException;
    userCv updateCv(userCv cv,MultipartFile file) throws CvNotFoundException, IOException;
    userCv findCvByCvId(int cvId) throws CvNotFoundException;
}
