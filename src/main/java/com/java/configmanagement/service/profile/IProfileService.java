package com.java.configmanagement.service.profile;

import com.java.configmanagement.entity.Application;
import com.java.configmanagement.entity.Profile;

import java.util.List;

public interface IProfileService {
    Profile createProfile(Profile  profile,String applicationId);

    Profile getProfileById(String Id);

    List<Profile> getProfiles();

    Profile updateProfile(Profile profile,String applicationId);

    void deleteProfile(String userId);
}
