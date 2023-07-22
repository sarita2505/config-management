package com.java.configmanagement.service;

import com.java.configmanagement.entity.Application;
import com.java.configmanagement.exception.AppRuntimeException;
import com.java.configmanagement.exception.ValidationException;
import com.java.configmanagement.repository.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements IApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    private static boolean validateApplicationName(String inputString) {
        String pattern = "^[A-Za-z -]+$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(inputString);
        return matcher.matches();
    }

    @Override
    public Application createApplication(Application application) {
        Application save = null;
        try {
            if (validateApplicationName(application.getName())) {
                save = applicationRepository.save(application);
            } else {
                log.print("invalid input provided");
                throw new ValidationException("Validation Failed invalid input provided !!! ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return save;
    }

    @Override
    public Application getApplicationtById(String Id) {
        Optional<Application> optionalUser = applicationRepository.findById(Id);
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
    public List<Application> getApplications() {
        List<Application> all = null;
        try {
            all = applicationRepository.findAll();
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage());
        }
        return all;
    }

    @Override
    public Application updateApplication(Application application) {
        Application existingApplication = applicationRepository.findById(application.getId()).get();
        existingApplication.setId(application.getId());
        existingApplication.setName(application.getName());
        existingApplication.setProfiles(application.getProfiles());
        Application updatedroduct = null;
        try {
            updatedroduct = applicationRepository.save(existingApplication);
        } catch (RuntimeException e) {
            throw e;
        }
        return updatedroduct;
    }

    @Override
    public void deleteApplication(String id) {
        try {
            applicationRepository.deleteById(id);
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage());
        }
    }
}

