package com.java.configmanagement.controller;

import com.java.configmanagement.entity.Configuration;
import com.java.configmanagement.entity.Profile;
import com.java.configmanagement.service.configuration.IConfigurationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("config")
public class ConfigController {
    private IConfigurationService configurationService;

    @PostMapping("/{profileId}")
    public ResponseEntity<Configuration> createProfile(@RequestBody Configuration configuration, @PathVariable String profileId) {
        Configuration serviceConfiguration = configurationService.createConfiguration(configuration,profileId);
        ResponseEntity<Configuration> productResponseEntity = null;
        productResponseEntity = new ResponseEntity<>(serviceConfiguration, HttpStatus.CREATED);
        return productResponseEntity;
    }

    @GetMapping("{id}")
    public ResponseEntity<Configuration> getProfileById(@PathVariable("id") String id) {
        Configuration configuration = configurationService.getConfigurationById(id);
        if (configuration.getId() != null) {
            return new ResponseEntity<>(configuration, HttpStatus.OK);
        }

        return null;

    }

    @GetMapping
    public ResponseEntity<List<Configuration>> getAllProfiles() {
        List<Configuration> configuration = configurationService.getConfiguration();
        ResponseEntity<List<Configuration>> listResponseEntity = null;
        listResponseEntity = new ResponseEntity<>(configuration, HttpStatus.OK);
        return listResponseEntity;
    }

    @PutMapping("{id}")
    public ResponseEntity<Configuration> updateApplication(@PathVariable("id") String configurationId,
                                                         @RequestBody Configuration configuration) {
        configuration.setId(configurationId);
        Configuration updateApplication = configurationService.updateConfiguration(configurationId,configuration);
        ResponseEntity<Configuration> productResponseEntity = null;
        productResponseEntity = new ResponseEntity<>(updateApplication, HttpStatus.OK);
        return productResponseEntity;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable("id") String userId) {
        configurationService.deleteConfiguration(userId);
        ResponseEntity<String> stringResponseEntity = null;
        stringResponseEntity = new ResponseEntity<>("configuration successfully deleted!", HttpStatus.OK);
        return stringResponseEntity;
    }
}
