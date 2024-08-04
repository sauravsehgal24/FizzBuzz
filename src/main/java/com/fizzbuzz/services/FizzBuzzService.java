package com.fizzbuzz.services;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fizzbuzz.enums.FizzBuzzResponseKey;

@Service
public class FizzBuzzService {

    private int nextNumberInSequence=0;

    /**
     * loop through 1-100 and append result into response object.
     * if multiple of 3, then append Fizz, if multiple of 5 then append Buzz, 
     * if multiple of both then append FizzBuzz else append the number itself
     * @param responseObject - response object to be updated based on the business logic. contains result, message, and http code
     * @return void
     * @author saurav sehgal
     */
    public HashMap<FizzBuzzResponseKey,Object>  processFizzBuzzForSequence(){

        // initialize the default response
        HashMap<FizzBuzzResponseKey,Object> responseObject = new HashMap<>();
        responseObject.put(FizzBuzzResponseKey.message,"Internal Error");
        responseObject.put(FizzBuzzResponseKey.result,null);
        responseObject.put(FizzBuzzResponseKey.httpStatusCode,HttpStatus.INTERNAL_SERVER_ERROR);

        try{
            HashMap<Integer,String> resultMap = new HashMap<>();
            for (int i=1; i<=100; i++){
                if(i % 3 == 0 && i % 5 == 0) resultMap.put(i, "FizzBuzz");
                else if(i % 3 == 0) resultMap.put(i, "Fizz");
                else if(i % 5 == 0) resultMap.put(i, "Buzz");
                else resultMap.put(i, Integer.toString(i));
            }
            responseObject.put(FizzBuzzResponseKey.result,resultMap);
            responseObject.put(FizzBuzzResponseKey.message,"Successfuly processed the sequence!");
            responseObject.put(FizzBuzzResponseKey.httpStatusCode,HttpStatus.OK);
        }
        // mimic some sort of exception and alter the response object, although its hard for program to raise exception in this logic
        catch(Exception e){
            // if there is a logger, then add the exception to logger.error()
            responseObject.put(FizzBuzzResponseKey.message,"Error Processing the Fizz Buzz Sequence!"); // error can be different based on exception
            responseObject.put(FizzBuzzResponseKey.httpStatusCode,HttpStatus.INTERNAL_SERVER_ERROR); // Code can be different based on the exception
        }
        return responseObject;
    }

    /**
     * check the next number in sequence of 1-100. if its multiple of 3 then append Fizz in result, if multiple of 5 then Buzz,
     * if multiple of both then FizzBuzz, otherwise append number itself in the result
     * @param responseObject - response object to be updated based on the business logic. contains result, message, and http code
     * @return void
     * @author saurav sehgal
     */
    public HashMap<FizzBuzzResponseKey,Object>  processNextNumInSequence(){

        // initialize the default response
        HashMap<FizzBuzzResponseKey,Object> responseObject = new HashMap<>();
        responseObject.put(FizzBuzzResponseKey.message,"Internal Error");
        responseObject.put(FizzBuzzResponseKey.result,null);
        responseObject.put(FizzBuzzResponseKey.httpStatusCode,HttpStatus.INTERNAL_SERVER_ERROR);

        try{
            nextNumberInSequence++;
            HashMap<Integer,String> result = new HashMap<>();
            if(nextNumberInSequence == 101) nextNumberInSequence=1;
            result.put(nextNumberInSequence,Integer.toString(nextNumberInSequence));
            if(nextNumberInSequence % 3 == 0 && nextNumberInSequence % 5 == 0) result.put(nextNumberInSequence,"FizzBuzz");
            else if(nextNumberInSequence % 3 == 0) result.put(nextNumberInSequence,"Fizz");
            else if(nextNumberInSequence % 5 == 0) result.put(nextNumberInSequence,"Buzz");
            responseObject.put(FizzBuzzResponseKey.result,result);
            responseObject.put(FizzBuzzResponseKey.message,"Successfuly processed the number in sequence!");
            responseObject.put(FizzBuzzResponseKey.httpStatusCode,HttpStatus.OK);
        }catch (Exception e){
            // if there is a logger, then add the exception to logger.error()
            responseObject.put(FizzBuzzResponseKey.message,"Error Processing the number: "+nextNumberInSequence); 
            responseObject.put(FizzBuzzResponseKey.httpStatusCode,HttpStatus.INTERNAL_SERVER_ERROR);

            //revert to previous number
            nextNumberInSequence--;
        }
        return responseObject;
    }
}
