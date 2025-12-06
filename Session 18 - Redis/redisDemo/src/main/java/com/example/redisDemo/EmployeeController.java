package com.example.redisDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private static final String EMPLOYEE_KEY_PREFIX = "employee::";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/employee")
    public void saveEmployee(@RequestBody Employee employee){
        redisTemplate.opsForValue().set(getKey(employee.getId()), employee);
    }

    private String getKey(long id){
        return EMPLOYEE_KEY_PREFIX + id;
    }
}
