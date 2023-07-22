package com.java.configmanagement.service.profile;

import com.java.configmanagement.entity.Application;
import com.java.configmanagement.entity.Profile;
import com.java.configmanagement.exception.AppRuntimeException;
import com.java.configmanagement.exception.ValidationException;
import com.java.configmanagement.repository.ProfileRepository;
import com.java.configmanagement.service.ApplicationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements IProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ApplicationServiceImpl applicationService;

    @Override
    public Profile createProfile(Profile profile,String applicationId) {
        Profile save = null;
        Application application=applicationService.getApplicationtById(applicationId);
        try {
            profile.setApplication(application);
            save = profileRepository.save(profile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return save;
    }

    @Override
    public Profile getProfileById(String Id) {
        Optional<Profile> optionalUser = profileRepository.findById(Id);
        try {
            if (optionalUser.isPresent()) {
                return optionalUser.get();
            } else {
                throw new AppRuntimeException("resource for the id is not present");
            }
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Profile> getProfiles() {
        List<Profile> all = null;
        try {
            all = profileRepository.findAll();
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage());
        }
        return all;
    }

    @Override
    public Profile updateProfile(Profile profile,String applicationId) {
        Profile existingApplication = profileRepository.findById(profile.getId()).get();
        existingApplication.setId(profile.getId());
        existingApplication.setName(profile.getName());
        existingApplication.setVersion(profile.getVersion());
        existingApplication.setStatus(profile.getStatus());
        Application application=applicationService.getApplicationtById(applicationId);
        Profile updateProfile = null;
        try {
            profile.setApplication(application);
            updateProfile = profileRepository.save(existingApplication);
        } catch (RuntimeException e) {
            throw e;
        }
        return updateProfile;
    }

    @Override
    public void deleteProfile(String id) {
        try {
            profileRepository.deleteById(id);
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage());
        }
    }
}

