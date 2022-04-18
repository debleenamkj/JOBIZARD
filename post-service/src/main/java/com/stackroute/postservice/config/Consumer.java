package com.stackroute.postservice.config;

import com.stackroute.postservice.Exception.UserAlreadyExistsException;
import com.stackroute.postservice.model.User;
import com.stackroute.postservice.service.PostService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Consumer {
    @Autowired
    private PostService postService;
    private int id;

    @RabbitListener(queues = "user_queue")
    public void getUser(MultipartFile file, User user) throws UserAlreadyExistsException {
        postService.saveUser(file,user);
//        recommendationService.saveUser(seeker);
    }

//    @RabbitListener(queues = "job_queue")
//    public void getJob(JobDetails job) throws JobAlreadyPresentException {
//        recommendationService.savejob(job);
//    }
}
