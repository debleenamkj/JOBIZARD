package com.stackroute.cvgenerationservice.config;

import com.stackroute.cvgenerationservice.Dto.JobSeekerDTO;
import com.stackroute.cvgenerationservice.domain.JobSeeker;
import com.stackroute.cvgenerationservice.exception.CvAlreadyExistsException;
import com.stackroute.cvgenerationservice.exception.CvNotFoundException;
import com.stackroute.cvgenerationservice.service.cvService;
import com.stackroute.cvgenerationservice.service.cvServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class Consumer {

    @Autowired
    private cvService CvService;

    @RabbitListener(queues = "cvGeneration_queue")
    public void getCv(JobSeeker cv) throws CvAlreadyExistsException, CvNotFoundException, IOException {
        System.out.println(cv);
        MultipartFile file = null;
        CvService.saveCv(cv,file);
    }
}
