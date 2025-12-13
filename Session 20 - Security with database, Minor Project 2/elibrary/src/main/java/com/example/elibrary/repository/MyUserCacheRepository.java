package com.example.elibrary.repository;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.elibrary.models.MyUser;

@Repository
public class MyUserCacheRepository {

    private final String USER_KEY_PREFIX = "usr::";


    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private String getKey(String username){
        return USER_KEY_PREFIX + username;
    }

    public void set(MyUser myUser){
        String key = getKey(myUser.getUsername());
        redisTemplate.opsForValue().set(key, myUser, 24, TimeUnit.HOURS);
    }

    public MyUser get(String username){
        String key = getKey(username);
        return (MyUser) redisTemplate.opsForValue().get(key);
    }
}
