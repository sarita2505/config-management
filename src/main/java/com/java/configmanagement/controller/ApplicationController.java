package com.java.configmanagement.controller;

import com.java.configmanagement.entity.Application;
import com.java.configmanagement.service.IApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("applications")
public class ApplicationController {
    private IApplicationService applicationService;

    @PostMapping
    public ResponseEntity<Application> createProduct(@RequestBody Application application) {
        Application savedApplication = applicationService.createApplication(application);
        ResponseEntity<Application> productResponseEntity = null;
        productResponseEntity = new ResponseEntity<>(savedApplication, HttpStatus.CREATED);
        return productResponseEntity;
    }

    @GetMapping("{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable("id") String id) {
        Application applicationtById = applicationService.getApplicationtById(id);
        if (applicationtById.getId() != null) {
            return new ResponseEntity<>(applicationtById, HttpStatus.OK);
        }

        return null;
    }

    @GetMapping
    public ResponseEntity<List<Application>> getAllApplications() {
        List<Application> applications = applicationService.getApplications();
        ResponseEntity<List<Application>> listResponseEntity = null;
        listResponseEntity = new ResponseEntity<>(applications, HttpStatus.OK);
        return listResponseEntity;
    }

    @PutMapping("{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable("id") String userId,
                                                         @RequestBody Application application) {
        application.setId(userId);
        Application updateApplication = applicationService.updateApplication(application);
        ResponseEntity<Application> productResponseEntity = null;
        productResponseEntity = new ResponseEntity<>(updateApplication, HttpStatus.OK);
        return productResponseEntity;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable("id") String userId) {
        applicationService.deleteApplication(userId);
        ResponseEntity<String> stringResponseEntity = null;
        stringResponseEntity = new ResponseEntity<>("application successfully deleted!", HttpStatus.OK);
        return stringResponseEntity;
    }
}
