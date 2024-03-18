package com.pragma.bootcamp.adapters.driving.http.controlleradvisor;

import com.pragma.bootcamp.domain.exception.DescriptionExceedsMaxLengthException;
import com.pragma.bootcamp.domain.exception.NameExceedsMaxLengthException;
import com.pragma.bootcamp.domain.exception.NoDataFoundException;
import com.pragma.bootcamp.domain.exception.TechnologyAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestAdvisorHandler {

    @ExceptionHandler(TechnologyAlreadyExistException.class)
    public ResponseEntity<Object> handleElementAlreadyExist (TechnologyAlreadyExistException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleNoDataFound(NoDataFoundException exception){
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(exception.getMessage());
    }
    @ExceptionHandler(NameExceedsMaxLengthException.class)
    public ResponseEntity<Object> handleMaxLengthNameTechnology(NameExceedsMaxLengthException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }
    @ExceptionHandler(DescriptionExceedsMaxLengthException.class)
    public ResponseEntity<Object> handleMaxLengthDescriptionTechnology(DescriptionExceedsMaxLengthException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exception.getMessage());

    }

}
