package com.example.spring_security_demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {

        httpSecurity.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/student").hasAuthority("student")
                        .requestMatchers("/admin").hasAuthority("admin")
                        .requestMatchers("/hello").permitAll()
                        .anyRequest().authenticated()
                ).httpBasic(httpBasic -> {})
                .formLogin(formLogin -> formLogin.defaultSuccessUrl("/home",true));
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity){
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser(User.builder().username("karan").password(getPE().encode("karan123")).authorities("student").build())
                .withUser(User.builder().username("ankit").password(getPE().encode("ankit123")).authorities("admin").build());
        return authenticationManagerBuilder.build();

    }

    @Bean
    PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }
}