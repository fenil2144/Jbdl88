package com.example.crud_jdbc_app.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.crud_jdbc_app.model.response.ErrorResponse;

@Configuration
@ControllerAdvice
public class GlobalExceptionHandler {

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
