package com.gameachievementsapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(value = {GameAchievementsException.class})
    public final ResponseEntity<ExceptionRespons> gameAchievementsNotFound(GameAchievementsException ex){
        ExceptionRespons exceptionRespons = new ExceptionRespons(ex.getMessage(),"Not found record");
        return new ResponseEntity<ExceptionRespons>(exceptionRespons, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {BindException.class })
    protected ResponseEntity<List<ObjectError>> validationException(BindException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getBindingResult().getAllErrors(), HttpStatus.BAD_REQUEST);
    }
}
