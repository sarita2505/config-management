package com.java.configmanagement.controller;

import com.java.configmanagement.entity.Profile;
import com.java.configmanagement.service.profile.IProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("profiles")
public class ProfileController {
    private IProfileService iProfileService;

    @PostMapping("/{applicationId}/profiles")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile, @PathVariable String applicationId) {
        Profile serviceProfile = iProfileService.createProfile(profile,applicationId);
        ResponseEntity<Profile> productResponseEntity = null;
        productResponseEntity = new ResponseEntity<>(serviceProfile, HttpStatus.CREATED);
        return productResponseEntity;
    }

    @GetMapping("{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable("id") String id) {
        Profile profileById = iProfileService.getProfileById(id);
        if (profileById.getId() != null) {
            return new ResponseEntity<>(profileById, HttpStatus.OK);
        }

        return null;

    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = iProfileService.getProfiles();
        ResponseEntity<List<Profile>> listResponseEntity = null;
        listResponseEntity = new ResponseEntity<>(profiles, HttpStatus.OK);
        return listResponseEntity;
    }

    @PutMapping("{id}")
    public ResponseEntity<Profile> updateApplication(@PathVariable("id") String userId,
                                                         @RequestBody Profile profile) {
        profile.setId(userId);
        Profile updateApplication = iProfileService.updateProfile(profile,userId);
        ResponseEntity<Profile> productResponseEntity = null;
        productResponseEntity = new ResponseEntity<>(updateApplication, HttpStatus.OK);
        return productResponseEntity;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable("id") String userId) {
        iProfileService.deleteProfile(userId);
        ResponseEntity<String> stringResponseEntity = null;
        stringResponseEntity = new ResponseEntity<>("application successfully deleted!", HttpStatus.OK);
        return stringResponseEntity;
    }
}
