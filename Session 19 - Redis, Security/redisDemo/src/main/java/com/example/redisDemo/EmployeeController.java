package com.example.redisDemo;

import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    ObjectMapper objectMapper;

    private static final String EMPLOYEE_KEY_PREFIX = "employee::";
    private static final String EMPLOYEE_LIST_KEY = "employeeList";
    private static final String EMPLOYEE_HASH_KEY_PREFIX = "employee_hash::";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/employee")
    public void saveEmployee(@RequestBody Employee employee) {
        redisTemplate.opsForValue().set(getKey(employee.getId()), employee);
    }

    private String getKey(long id) {
        return EMPLOYEE_KEY_PREFIX + id;
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable long id) {
        return (Employee) redisTemplate.opsForValue().get(getKey(id));
    }

    // List Operations (Key - String, Value - List<Employee>)
    @PostMapping("/employee/lpush")
    public void lpushEmployee(@RequestBody Employee employee) {
        redisTemplate.opsForList().leftPush(EMPLOYEE_LIST_KEY, employee);
    }

    @GetMapping("/employee/lrange")
    public List<Employee> lrangeEmployees(@RequestParam(value = "start", defaultValue = "0", required = false) int start,
                                          @RequestParam(value = "end", defaultValue = "-1", required = false) int end) {
        return redisTemplate.opsForList().range(EMPLOYEE_LIST_KEY, start, end)
                .stream().map(Employee.class::cast).toList();
    }

    // Set Operation

    // Hash Opertaion
    @PostMapping("/employee/hash")
    public void saveEmployeeInHash(@RequestBody List<Employee> employeeList) {
        employeeList
                .forEach(employee -> {
                    Map map = objectMapper.convertValue(employee, Map.class);
                    redisTemplate.opsForHash().putAll(getHashKey(employee.getId()), map);
                });
    }

    private String getHashKey(long id) {
        return EMPLOYEE_HASH_KEY_PREFIX + id;
    }

    @GetMapping("/employee/hash/all")
    public List<Employee> getEmployeeFromHash(@RequestParam("ids") List<Long> ids){
        return ids.stream()
                .map(i -> redisTemplate.opsForHash().entries(getHashKey(i)))
                .map(entryMap -> objectMapper.convertValue(entryMap, Employee.class))
                .collect(Collectors.toList());
    }
}