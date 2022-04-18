package com.stackroute.cvgenerationservice.config;

import com.stackroute.cvgenerationservice.domain.userCv;
import com.stackroute.cvgenerationservice.exception.CvAlreadyExistsException;
import com.stackroute.cvgenerationservice.exception.CvNotFoundException;
import com.stackroute.cvgenerationservice.service.cvService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class Consumer {

    @Autowired
    private cvService CvService;

    @RabbitListener(queues = "cvGeneration_queue")
    public void getCv(userCv cv, MultipartFile file) throws CvAlreadyExistsException, CvNotFoundException, IOException {
        CvService.saveCv(cv,file);
    }
}
