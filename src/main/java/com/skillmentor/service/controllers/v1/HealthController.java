package com.skillmentor.service.controllers.v1;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<String> getHealth(){
        return ResponseEntity.ok("Skill mentor");
    }
}
