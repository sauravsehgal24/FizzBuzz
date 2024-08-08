package com.fizzbuzz.services;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fizzbuzz.enums.FizzBuzzResponseKey;

import lombok.Getter;
import lombok.Setter;

@Service
@Setter
@Getter
public class FizzBuzzService {

    private static final Logger logger = LoggerFactory.getLogger(FizzBuzzService.class);

    private int nextNumberInSequence=1;

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
                String result=checkIsMultiple(i);
                resultMap.put(i, result);
                logger.info("Processed number "+i+" | Result = "+result);
            }
            responseObject.put(FizzBuzzResponseKey.result,resultMap);
            responseObject.put(FizzBuzzResponseKey.message,"Successfuly processed the sequence!");
            responseObject.put(FizzBuzzResponseKey.httpStatusCode,HttpStatus.OK);
        }
        // mimic some sort of exception and alter the response object, although its hard for program to raise exception in this logic
        catch(Exception e){
            logger.error(e.getMessage());
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
            HashMap<Integer,String> resultMap = new HashMap<>();
            String result = checkIsMultiple(nextNumberInSequence);
            resultMap.put(nextNumberInSequence, result);
            responseObject.put(FizzBuzzResponseKey.result,resultMap);
            responseObject.put(FizzBuzzResponseKey.message,"Successfuly processed the number in sequence!");
            responseObject.put(FizzBuzzResponseKey.httpStatusCode,HttpStatus.OK);
            logger.info("Processed number "+nextNumberInSequence+" | Result = "+result);
            nextNumberInSequence++;
        }catch (Exception e){
            logger.error(e.getMessage());
            responseObject.put(FizzBuzzResponseKey.message,"Error Processing the number: "+nextNumberInSequence); 
            responseObject.put(FizzBuzzResponseKey.httpStatusCode,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseObject;
    }

    // utility method
    private String checkIsMultiple(int number) throws Exception{
        if(number<0) throw new Exception("number cannot be negative");
        String result = number+"";
        if(number % 3 == 0 && number % 5 == 0) result="FizzBuzz";
        else if(number % 3 == 0) result="Fizz";
        else if(number % 5 == 0) result="Buzz";
        return result;
    }
}
