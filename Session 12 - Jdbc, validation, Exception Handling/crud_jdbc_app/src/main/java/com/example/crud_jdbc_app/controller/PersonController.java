package com.example.crud_jdbc_app.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud_jdbc_app.model.request.CreatePersonRequest;
import com.example.crud_jdbc_app.model.response.ErrorResponse;
import com.example.crud_jdbc_app.service.PersonService;

@Slf4j
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity createPerson(@RequestBody @Valid CreatePersonRequest createPersonRequest) throws BadRequestException {
        log.info("In PersonController.createPerson with createPersonRequest: {}", createPersonRequest);

        personService.createPerson(createPersonRequest);

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Sample Response Header", "Sample Value");
        return new ResponseEntity<>(createPersonRequest, headers, HttpStatus.CREATED);
    }

    @GetMapping("/{personId}")
    public ResponseEntity getPersonById(@PathVariable int personId){
        log.info("In PersonController.getPersonById");
        return new ResponseEntity(personService.getPersonById(personId), HttpStatus.OK);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exc) {
        BindingResult bindingResult = exc.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errorMessageList = fieldErrors.stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage()).toList();
        ErrorResponse errorResponse = new ErrorResponse(errorMessageList, HttpStatus.NOT_ACCEPTABLE,
                exc.getLocalizedMessage(), "ERR_001");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
