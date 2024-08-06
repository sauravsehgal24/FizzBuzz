package com.fizzbuzz.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.fizzbuzz.enums.FizzBuzzResponseKey;
import com.fizzbuzz.services.FizzBuzzService;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1")
public class FizzbuzzController {

    @Autowired
    FizzBuzzService fizzBuzzService;
    
    /**
     * GET - get the result response for sequence of 1-100 based on multiple of 5 or 3
     * @return response object containing result, message, http code
     * @author saurav sehgal
     */
    @GetMapping("/getFizzBuzzForSequence")    
    public ResponseEntity<Object> getFizzBuzzForSequence(){

        //process the sequence 1-100
        HashMap<FizzBuzzResponseKey,Object> responseResult = fizzBuzzService.processFizzBuzzForSequence();
        
        //return response for 1-100
        return new ResponseEntity<>(responseResult, (HttpStatus)responseResult.get(FizzBuzzResponseKey.httpStatusCode));
    }

     /**
     * GET - get the result response for next number in sequence of 1-100 based on multiple of 5 or 3
     * @return response object containing result, message, http code
     * @author saurav sehgal
     */
    @GetMapping("/next")
    public ResponseEntity<Object> getNextResultFromSequence() {

        // process the next number in sequence
        HashMap<FizzBuzzResponseKey,Object> responseResult = fizzBuzzService.processNextNumInSequence();

        //return response for next number in sequence
        return new ResponseEntity<>(responseResult, (HttpStatus)responseResult.get(FizzBuzzResponseKey.httpStatusCode));
    }

     /**
     * GET - reset the sequence to 1
     * @return response object containing result, message, http code
     * @author saurav sehgal
     */
    @GetMapping("/reset")
    public ResponseEntity<Object> resetSequence() {

        // process the next number in sequence
        fizzBuzzService.setNextNumberInSequence(1);

        //return response for next number in sequence
        return new ResponseEntity<>("Successfuly reseted the sequence to start from 1", HttpStatus.OK);
    }
    
    // create reset controller 
}
