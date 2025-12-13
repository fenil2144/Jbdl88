package com.example.springSecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/home")
    public String homeMethod(){
        return "Welcome to the spring security demo app!";
    }

    @GetMapping("/api/developer")
    public String developerCode() {
        return "Welcome Developer!";
    }

    @GetMapping("/api/tester")
    public String testerCode() {
        return "Welcome Tester!";
    }

    @GetMapping("/generalcode")
    public String generalCode() {
        return "Welcome Public!";
    }

    @PostMapping("/api/developer")
    public String developerPostMapping(){
        return "Hello Developer!";
    }


}
