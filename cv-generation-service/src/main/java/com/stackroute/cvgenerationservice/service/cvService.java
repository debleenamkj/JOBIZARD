package com.stackroute.cvgenerationservice.service;

import com.stackroute.cvgenerationservice.domain.JobSeeker;
import com.stackroute.cvgenerationservice.exception.CvAlreadyExistsException;
import com.stackroute.cvgenerationservice.exception.CvNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface cvService {
    public int getSequenceNumber(String sequenceName);
    JobSeeker saveCv(JobSeeker cv, MultipartFile file) throws CvAlreadyExistsException, IOException, CvNotFoundException;
    public void deleteCv(int cvId) throws CvNotFoundException;
    JobSeeker updateCv(int cvId, JobSeeker cv, MultipartFile file) throws CvNotFoundException, IOException;
    JobSeeker findCvByCvId(int cvId) throws CvNotFoundException;
    JobSeeker findByEmail(String email) throws CvNotFoundException;
}
