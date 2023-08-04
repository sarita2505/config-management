package com.java.configmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String version;
    private String name;
    private String status;
    @ManyToOne
    @JoinColumn(name = "application_id")
    @JsonBackReference
    private Application application;
    //    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "config_id", unique = true)
    @OneToOne(mappedBy = "profile",cascade = CascadeType.ALL)
    private Configuration configuration;
}

