package com.gfg;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.gfg.UserConstants.USER_AUTHORITY;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepositoryInterf userRepositoryInterf;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositoryInterf.findByPhoneNumber(username);
    }

    public void createUser(UserCreateRequest userCreateRequest){
        log.debug("In UserService.createUser with userCreateRequest: {}", userCreateRequest);
        User user = userCreateRequest.toUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(USER_AUTHORITY);
        user = userRepositoryInterf.save(user);

        // publish the event post user creation which will be listened by consumers
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.USER_CREATED_TOPIC_USER_ID, user.getId());
        jsonObject.put(CommonConstants.USER_CREATED_TOPIC_PHONE_NUMBER, user.getPhoneNumber());
        jsonObject.put(CommonConstants.USER_CREATED_TOPIC_EMAIL, user.getEmail());
        jsonObject.put(CommonConstants.USER_CREATED_TOPIC_IDENTIFIER_KEY, user.getUserIdentifier());
        jsonObject.put(CommonConstants.USER_CREATED_TOPIC_IDENTIFIER_VALUE, user.getIdentifiervalue());

        kafkaTemplate.send(CommonConstants.USER_CREATED_TOPIC, objectMapper.writeValueAsString(jsonObject));
        log.debug("Out UserService.createUser");
    }

    public List<User> getAlluserDetails(){
        return userRepositoryInterf.findAll();
    }
}
