package com.ngai.payment.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.ngai.payment.services.custom.ApiException;
import com.ngai.payment.utils.Utility;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<Object> handleApiException(ApiException ex, WebRequest request){
        return new ResponseEntity(new Gson().toJson(Utility.buildExceptionResponse(ex)), new HttpHeaders(), ex.getStatusCode());
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllExceptions(Exception exception){
        exception.printStackTrace();
        return new ResponseEntity<Object>(Utility.buildAllExceptionResponse(exception), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<Object> handleInvalidJson(JsonParseException ex, WebRequest request){
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message","Invalid Json");

        return new ResponseEntity(body, HttpStatus.ALREADY_REPORTED);
    }
}
