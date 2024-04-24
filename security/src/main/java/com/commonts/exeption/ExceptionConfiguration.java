package com.commonts.exeption;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionConfiguration extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = HamisheBaharException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> serviceException(
            HamisheBaharException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getCode(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<ErrorMessage>(message, new HttpHeaders() ,
                HttpStatus.BAD_REQUEST);
    }

    //try {
    //            this.classTest.methodTest(TEST);
    //    } catch (InterruptedException | ServiceUnavailableException e) {
    //            throw new MessengerExp("test" , e);
    //}
}
