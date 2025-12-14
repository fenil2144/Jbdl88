package com.example.elibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.elibrary.models.Admin;
import com.example.elibrary.models.MyUser;
import com.example.elibrary.models.Student;
import com.example.elibrary.models.enums.AccountStatus;
import com.example.elibrary.repository.AdminRepositoryInterf;
import com.example.elibrary.repository.MyUserRepository;
import com.example.elibrary.repository.StudentRepositoryInterf;

import static com.example.elibrary.constants.ApplicationConstants.ADMIN_AUTHORITY;
import static com.example.elibrary.constants.ApplicationConstants.STUDENT_AUTHORITY;

@SpringBootApplication
public class ElibraryApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	MyUserRepository myUserRepository;

	@Autowired
	AdminRepositoryInterf adminRepositoryInterf;

	@Autowired
	StudentRepositoryInterf studentRepositoryInterf;

	public static void main(String[] args) {
		SpringApplication.run(ElibraryApplication.class, args);
	}

	@Override
	public void run(String... args) {

//		MyUser user1 = MyUser.builder()
//				.username("ankit").password(passwordEncoder.encode("ankit123"))
//				.authority(ADMIN_AUTHORITY)
//				.build();
//		user1 = myUserRepository.save(user1);
//
//		Admin admin = Admin.builder()
//				.age(45).name("Ankit G").myUser(user1).build();
//		adminRepositoryInterf.save(admin);
//
//		MyUser user2 = MyUser.builder()
//				.username("kishan").password(passwordEncoder.encode("kishan123"))
//				.authority(STUDENT_AUTHORITY)
//				.build();
//		user2 = myUserRepository.save(user2);
//
//		Student student = Student.builder()
//				.name("Kishan S").email("kishan@gmail.com")
//				.accountStatus(AccountStatus.ACTIVE)
//				.phone("9878787871").myUser(user2).build();
//		studentRepositoryInterf.save(student);
	}
}
