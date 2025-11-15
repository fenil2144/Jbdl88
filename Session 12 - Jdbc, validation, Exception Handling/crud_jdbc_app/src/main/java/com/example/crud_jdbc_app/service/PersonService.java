package com.example.crud_jdbc_app.service;

import java.time.LocalDate;
import java.time.Period;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import com.example.crud_jdbc_app.model.Person;
import com.example.crud_jdbc_app.model.request.CreatePersonRequest;
import com.example.crud_jdbc_app.repository.PersonRepository;

@Configuration
@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;


    public String createPerson(CreatePersonRequest createPersonRequest) throws BadRequestException {

        Person person = createPersonRequest.to();

        // Not reuired with use of validation annotations
//        if (person.getId() == 0) {
//            throw new BadRequestException("Person ID is required");
//        }
//        if (!StringUtils.hasText(person.getFirstName())) {
//            throw new BadRequestException("First Name is required");
//        }
        if (person.getAge() == null || person.getAge() == 0) {
            person.setAge(calculateAgeFromDOB(person.getDob()));
        }
        return personRepository.savePerson(person);
    }

    private Integer calculateAgeFromDOB(String dob) {
        if (StringUtils.hasText(dob)) {
            LocalDate dobDate = LocalDate.parse(dob);
            LocalDate currentDate = LocalDate.now();
            return Period.between(dobDate, currentDate).getYears();
        }
        return 0;
    }


    public Person getPersonById(int personId) {
        return personRepository.getPersonById(personId);
    }
}
