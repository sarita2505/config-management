package com.java.configmanagement.service.configuration;

import com.java.configmanagement.entity.Configuration;

import java.util.List;

public interface IConfigurationService {
    Configuration createConfiguration(Configuration  configuration, String applicationId);

    Configuration getConfigurationById(String Id);

    List<Configuration> getConfiguration();

    Configuration updateConfiguration(String applicationId,Configuration configuration);

    void deleteConfiguration(String userId);
}
