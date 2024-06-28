package com.maven.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Data
@Component
@Table(name = "register")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String userEmail;
    @Column(unique = true,length = 8)
    private String password;
}
