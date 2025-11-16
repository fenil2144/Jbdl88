package com.example.crud_jdbc_app.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud_jdbc_app.model.Person;
import com.example.crud_jdbc_app.model.request.PersonRequest;
import com.example.crud_jdbc_app.model.response.ErrorResponse;
import com.example.crud_jdbc_app.service.PersonService;

@Slf4j
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity createPerson(@RequestBody @Valid PersonRequest personRequest) throws BadRequestException {
        log.info("In PersonController.createPerson with personRequest: {}", personRequest);

        personService.createPerson(personRequest);

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Sample Response Header", "Sample Value");
        return new ResponseEntity<>(personRequest, headers, HttpStatus.CREATED);
    }

    @GetMapping("/{personId}")
    public ResponseEntity getPersonById(@PathVariable int personId){
        log.info("In PersonController.getPersonById");
        return new ResponseEntity(personService.getPersonById(personId), HttpStatus.OK);
    }

    @PutMapping("/{personId}")
    public ResponseEntity updatePerson(@PathVariable int personId, @RequestBody PersonRequest personRequest){
        log.info("In PersonController.updatePerson with personId: {} and personRequest: {}", personId, personRequest);
        Person updatedPerson = personService.updatePerson(personId, personRequest);
        return new ResponseEntity(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity deletePerson(@PathVariable int personId){
        log.info("In PersonController.deletePerson with personId: {}", personId);
        personService.deletePerson(personId);
        return new ResponseEntity<>("Person deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Person> getAllPersons(){
        return personService.getAllPersons();
    }

}
