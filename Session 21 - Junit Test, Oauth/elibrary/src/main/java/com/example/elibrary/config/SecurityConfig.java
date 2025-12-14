package com.example.elibrary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.elibrary.constants.ApplicationConstants.ADMIN_AUTHORITY;
import static com.example.elibrary.constants.ApplicationConstants.STUDENT_AUTHORITY;
import static org.springframework.http.HttpMethod.POST;

@Configuration
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        httpSecurity.authorizeHttpRequests(auth ->
                        auth.requestMatchers(POST, "/student").permitAll()
                                .requestMatchers("/student/**").hasAuthority(STUDENT_AUTHORITY)
                                .requestMatchers(POST,"/books").hasAuthority(ADMIN_AUTHORITY)
                                .requestMatchers("/books").hasAnyAuthority(STUDENT_AUTHORITY, ADMIN_AUTHORITY)
                                .requestMatchers("/generalcode").hasAnyAuthority(STUDENT_AUTHORITY, ADMIN_AUTHORITY)
                                .requestMatchers("/home").permitAll()
                                .anyRequest().authenticated()
                ).httpBasic(httpBasic -> {})
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(formLogin -> formLogin.defaultSuccessUrl("/home", true).permitAll())
                .logout(LogoutConfigurer::permitAll);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(getPE());
        return authenticationManagerBuilder.build();
    }

    @Bean
    PasswordEncoder getPE() {
        return new BCryptPasswordEncoder();
    }

}