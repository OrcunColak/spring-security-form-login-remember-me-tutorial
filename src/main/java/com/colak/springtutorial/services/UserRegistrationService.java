package com.colak.springtutorial.services;

import com.colak.springtutorial.entity.User;
import com.colak.springtutorial.repositories.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    @Autowired
    PasswordEncoder pwdEnc;

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    public void createUser(User user) {
        user.setPassword(pwdEnc.encode(user.getPassword()));
        userRegistrationRepository.save(user);
    }

    public User getUserByUsername(String username) {
      return  userRegistrationRepository.findByUsername(username).orElseThrow();
    }
}
