package com.skillmentor.service.controllers.v2;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v2/health")
public class HealthControllerV2 {

    @GetMapping
    public String getHealth(){
        return "New Skill Mentor backend is running";
    }
}
