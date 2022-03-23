package com.stackroute.cvgenerationservice.controller;

import com.stackroute.cvgenerationservice.service.cvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class controller {
    private cvService service;

    @Autowired

    public controller(cvService service) {
        this.service = service;
    }
}
