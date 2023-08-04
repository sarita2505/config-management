package com.java.configmanagement.service.configuration;

import com.java.configmanagement.entity.Application;
import com.java.configmanagement.entity.Configuration;
import com.java.configmanagement.entity.Profile;
import com.java.configmanagement.exception.AppRuntimeException;
import com.java.configmanagement.repository.ConfigRepository;
import com.java.configmanagement.service.profile.ProfileServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfigurationServiceImpl implements IConfigurationService {
    @Autowired
    private ConfigRepository configRepository;
    @Autowired
    private ProfileServiceImpl profileService;

    @Override
    public Configuration createConfiguration(Configuration configuration, String applicationId) {
        Configuration save = null;
        Profile profile= profileService.getProfileById(applicationId);
        try {
            configuration.setProfile(profile);
            save = configRepository.save(configuration);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return save;
    }

    @Override
    public Configuration getConfigurationById(String Id) {
        Optional<Configuration> optionalUser = configRepository.findById(Id);
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
    public List<Configuration> getConfiguration() {
        List<Configuration> all = null;
        try {
            all = configRepository.findAll();
        } catch (Exception e) {
            throw new AppRuntimeException(e.getMessage());
        }
        return all;
    }

    @Override
    public Configuration updateConfiguration(String profileId,Configuration configuration) {
        Configuration configurationFromDb = configRepository.findById(configuration.getId()).get();
        configurationFromDb.setContent(configuration.getContent());
        configurationFromDb.setVersion(configuration.getVersion());
        Configuration configuration1 = null;
        try {
            configuration1 = configRepository.save(configurationFromDb);
        } catch (RuntimeException e) {
            throw e;
        }
        return configuration1;
    }

    @Override
    public void deleteConfiguration(String id) {
        try {
            Configuration configuration = configRepository.findById(id).get();
            configuration.setProfile(null);
            configRepository.save(configuration);

            configRepository.deleteById(id);

            System.out.println("deleted");
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppRuntimeException(e.getMessage());
        }
    }
}

