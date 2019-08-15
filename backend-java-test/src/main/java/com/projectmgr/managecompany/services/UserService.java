package com.projectmgr.managecompany.services;

import com.projectmgr.managecompany.exceptions.UsernameAlreadyExistsException;
import com.projectmgr.managecompany.models.User;
import com.projectmgr.managecompany.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    //this is basically so we can encode or password so that we don't store strength Spring Security provide our shield
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser (User newUser){

        try {
            //happy path
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            //Username has to be unique (exception )
            newUser.setUsername(newUser.getUsername());

            //Make sure that password and configPassword match
            //We don't persist or show the configPassword
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername() + "' already exists");
        }
    }
}

