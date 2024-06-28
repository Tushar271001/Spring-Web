package com.maven.services;

import com.maven.entities.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public interface UserService {
  Serializable addUser(User user);

  User loginUser(User user);
}
