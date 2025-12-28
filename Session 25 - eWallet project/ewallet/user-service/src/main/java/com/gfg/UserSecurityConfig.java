package com.gfg;

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

import static com.gfg.UserConstants.ADMIN_AUTHORITY;
import static com.gfg.UserConstants.USER_AUTHORITY;
import static org.springframework.http.HttpMethod.POST;

@Configuration
public class UserSecurityConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        httpSecurity.authorizeHttpRequests(auth ->
                        auth.requestMatchers(POST, "/user/**").permitAll()
                                .requestMatchers("/user/**").hasAuthority(USER_AUTHORITY)
                                .requestMatchers("/admin").hasAuthority(ADMIN_AUTHORITY)
                                .anyRequest().authenticated()
                ).httpBasic(httpBasic -> {})
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(formLogin -> formLogin.defaultSuccessUrl("/home", true).permitAll())
                .logout(LogoutConfigurer::permitAll);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }
}
