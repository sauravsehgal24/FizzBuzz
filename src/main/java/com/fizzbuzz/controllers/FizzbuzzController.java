package com.fizzbuzz.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.fizzbuzz.services.FizzBuzzService;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class FizzbuzzController {

    @Autowired
    FizzBuzzService fizzBuzzService;
    
    @GetMapping("/getFizzBuzzForSequence")    
    public ResponseEntity<Object> getFizzBuzzForSequence(){
        // default response object
        HashMap<String,Object> responseObject = new HashMap<>();
        responseObject.put("message","Internal Error Processing the Sequence");
        responseObject.put("httpStatusCode",HttpStatus.INTERNAL_SERVER_ERROR);
        responseObject.put("result",null);

        //process the sequence 1-100
        fizzBuzzService.processFizzBuzzForSequence(responseObject);

        //return response for 1-100
        return new ResponseEntity<>(responseObject, (HttpStatus)responseObject.get("httpStatusCode"));

    }

    @GetMapping("/getNextInSequence")
    public ResponseEntity<Object> getNextResultFromSequence() {
        // default response object
        HashMap<String,Object> responseObject = new HashMap<>();
        responseObject.put("message","Internal Error Processing next value!");
        responseObject.put("httpStatusCode",HttpStatus.INTERNAL_SERVER_ERROR);
        responseObject.put("result",null);

        fizzBuzzService.processNextNumInSequence(responseObject);

        return new ResponseEntity<>(responseObject, (HttpStatus)responseObject.get("httpStatusCode"));
    }
    
}
