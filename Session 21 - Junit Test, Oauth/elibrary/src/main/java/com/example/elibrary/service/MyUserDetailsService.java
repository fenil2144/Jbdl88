package com.example.elibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.elibrary.models.MyUser;
import com.example.elibrary.repository.MyUserCacheRepository;
import com.example.elibrary.repository.MyUserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    MyUserRepository myUserRepository;

    @Autowired
    MyUserCacheRepository myUserCacheRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = myUserCacheRepository.get(username); // find details from cache
        if (myUser == null) {
            myUser = myUserRepository.findByUsername(username); // find details from database

            if (myUser != null) {
                myUserCacheRepository.set(myUser);
            } else {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
        }

        return myUser;
    }
}
