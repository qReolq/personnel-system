package qreol.projects.personnel_system.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import qreol.projects.personnel_system.domain.exceptions.ResourceNotFoundException;
import qreol.projects.personnel_system.domain.exceptions.ResourceNotValidException;
import qreol.projects.personnel_system.web.dto.ExceptionBody;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourceNotFound(ResourceNotFoundException e) {
        return new ExceptionBody(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ResourceNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourceNotValid(ResourceNotValidException e) {
        return new ExceptionBody(e.getMessage(), LocalDateTime.now());
    }

}
