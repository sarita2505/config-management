package com.java.configmanagement.service;

import com.java.configmanagement.entity.Application;

import java.util.List;

public interface IApplicationService {
    Application createApplication(Application  application);

    Application getApplicationtById(String Id);

    List<Application> getApplications();

    Application updateApplication(Application application);

    void deleteApplication(String userId);
}
