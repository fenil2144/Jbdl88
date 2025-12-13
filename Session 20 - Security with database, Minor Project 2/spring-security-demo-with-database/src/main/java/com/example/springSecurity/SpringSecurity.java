package com.example.springSecurity;

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

import static org.springframework.http.HttpMethod.POST;

@Configuration
public class SpringSecurity {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        httpSecurity.authorizeHttpRequests(auth ->
            auth.requestMatchers(POST, "/api/developer").hasAuthority("developer")
                    .requestMatchers("/api/developer").hasAuthority("developer")
                    .requestMatchers("/api/tester/**").hasAuthority("tester")
                    .requestMatchers("/generalcode").hasAnyAuthority("developer", "tester")
                    .requestMatchers("/home").permitAll()
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
        authenticationManagerBuilder.userDetailsService(userDetailsService);
        return authenticationManagerBuilder.build();
    }

    @Bean
    PasswordEncoder getPE() {
        return new BCryptPasswordEncoder();
    }

    // 1) Role based access/permissions: Request matchers in security filter chain will have multiple
    // roles defined for set of API's  and user in database will have single role.
    // 2) Action based permission: Request matcher will have single role for set of API's and
    // user in database will have multiple actions in authority column
    // 3) Combination of both: Ideally suitable and preferable
}
