package com.example.springSecurity;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityDemoWithDatabaseApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	MyUserRepository myUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoWithDatabaseApplication.class, args);
	}

	// Run immediately after application startup
	@Override
	public void run(String... args) {
		MyUser user1 = MyUser.builder()
				.name("ankit").email("ankit@gmail.com")
				.password(passwordEncoder.encode("ankit123"))
				.authority("developer")
				.build();

		MyUser user2 = MyUser.builder()
				.name("kishan").email("kishan@gmail.com")
				.password(passwordEncoder.encode("kishan123"))
				.authority("tester")
				.build();
		myUserRepository.saveAll(Arrays.asList(user1, user2));
	}
}
