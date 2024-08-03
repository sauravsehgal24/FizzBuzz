package com.fizzbuzz.services;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FizzBuzzService {

    private int nextNumberInSequence=0;

    public void processFizzBuzzForSequence(HashMap<String,Object> responseObject){
        try{
            HashMap<Integer,String> resultMap = new HashMap<>();
            for (int i=1; i<=100; i++){
                if(i % 3 == 0 && i % 5 == 0) resultMap.put(i, "FizzBuzz");
                else if(i % 3 == 0) resultMap.put(i, "Fizz");
                else if(i % 5 == 0) resultMap.put(i, "Buzz");
                else resultMap.put(i, Integer.toString(i));
            }
            responseObject.put("result",resultMap);
            responseObject.put("message","Successfuly processed the sequence!");
            responseObject.put("httpStatusCode",HttpStatus.OK);
        }catch(Exception e){
            // if there is a logger, then add the exception to logger.error()
            responseObject.put("message","Error Processing the Fizz Buzz Sequence!"); // error can be different based on exception
            responseObject.put("httpStatusCode",HttpStatus.INTERNAL_SERVER_ERROR); // Code can be different based on the exception
        }
    }

    public void processNextNumInSequence(HashMap<String,Object> responseObject){
        nextNumberInSequence++;
        HashMap<Integer,String> result = new HashMap<>();
        if(nextNumberInSequence == 101) nextNumberInSequence=1;
        result.put(nextNumberInSequence,Integer.toString(nextNumberInSequence));
        if(nextNumberInSequence % 3 == 0 && nextNumberInSequence % 5 == 0) result.put(nextNumberInSequence,"FizzBuzz");
        else if(nextNumberInSequence % 3 == 0) result.put(nextNumberInSequence,"Fizz");
        else if(nextNumberInSequence % 5 == 0) result.put(nextNumberInSequence,"Buzz");
        responseObject.put("result", result);
        responseObject.put("message","Successfuly processed next number in sequence!");
        responseObject.put("httpStatusCode",HttpStatus.OK);
    }
}
