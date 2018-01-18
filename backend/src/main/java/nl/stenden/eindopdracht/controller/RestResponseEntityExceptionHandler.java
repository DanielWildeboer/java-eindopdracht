package nl.stenden.eindopdracht.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { NullPointerException.class })
    protected ResponseEntity<Object> handleNullPointer(RuntimeException e, WebRequest request){
        String response = "You done goofed mate";
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
}
