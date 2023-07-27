package com.java.configmanagement.repository;

import com.java.configmanagement.entity.Configuration;
import com.java.configmanagement.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<Configuration,String> {

}
