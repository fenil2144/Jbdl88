package com.example.spring_security_demo;

import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Security!";
    }

    // Before Login - 4210C044142096562D0304286F486A53
    // After Login - FEA6279D8C84D81090968E0E497D04B2

    // Before Login - JSESSIONID=44DA6B49F5E859DE919F75286CA62622
    // After Login - JSESSIONID=13D823AEE1ADB475F353F1D0FA1E2F06

    // Request comes to backend:
    //    1) JSESSIONID sent in Cookie Header is of Unauthenticated session/user:
    //        BE: Verifies the session id and redirects to /login page.
    //        FE: Browser calls /login and backend return the html code for login page
    //        FE: Adds the credentials in login page and calls /login - GET API
    //        BE: Verify the credentials based on username and password and generates
    //            new authenticated JSESSIONID and pass it in response headers as cookies.
    //        FE: Stores the new JSESSIONID in browser cookies and use this new authenticated
    //            JSESSIONID for later requests.
    //    2) JSESSIONID sent is of authenticated session/user:
    //        BE: Verifies the session id and process the request and return the response.

    // Password: 6ff01ca6-7c3a-4052-bef6-60138490c8a6
    // Password: 17ba8ee3-e2b4-4ca4-9f98-88b58dbdc3fd

    @GetMapping("/home")
    public String home(){
        return "Welcome to the home page!";
    }

    @GetMapping("/student")
    public String student(){
        return "Welcome Student!";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Welcome Admin!";
    }
}
