package com.fizzbuzz.unit.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fizzbuzz.enums.FizzBuzzResponseKey;
import com.fizzbuzz.services.FizzBuzzService;

@SpringBootTest
@DisplayName("FizzBuzzService Tests")
public class FizzBuzzServiceTest {

    @Autowired
    FizzBuzzService fizzBuzzService;

    @Test
    @DisplayName("contextLoads -> assert if fizzBuzzService serivce is loaded in memory")
	void contextLoads() throws Exception {
		assertNotNull(fizzBuzzService);
	}
    
    @Test()
    @DisplayName("processFizzBuzzForSequence -> assert if the function is generating expected result for 1-100 sequence")
    void processFizzBuzzForSequenceTest(){
        // act
        HashMap<FizzBuzzResponseKey,Object> responseObject = fizzBuzzService.processFizzBuzzForSequence();

        // assert
        HashMap<Integer,String> result = (HashMap<Integer,String>) responseObject.get(FizzBuzzResponseKey.result);
        assertNotNull(result);
        assertEquals(100, result.size());
        
        // assert all the numbers in the result set 1-100
        for(int i=1; i<=100; i++){
            String expectedValue = Integer.toString(i);
            if(i % 3 == 0 && i % 5 == 0) expectedValue="FizzBuzz";
            else if(i % 3 == 0) expectedValue="Fizz";
            else if(i % 5 == 0) expectedValue="Buzz";

            String actualValue = result.get(i);

            assertNotNull(actualValue);
            assertEquals(expectedValue, actualValue);
        }

    }

    @Test()
    @DisplayName("processNextNumInSequence -> assert if the function is generating expected result for next number in sequence of 1-100")
    void processNextNumInSequenceTest() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{

        // get the private field in service nextNumberInSequence
        Field _nextNumberInSequenceField = fizzBuzzService.getClass().getDeclaredField("nextNumberInSequence");
        _nextNumberInSequenceField.setAccessible(true);
        
        // assert all the numbers in the result set 1-100
        for(int i=1; i<=100; i++){

            //act
            HashMap<FizzBuzzResponseKey,Object> responseObject = fizzBuzzService.processNextNumInSequence();
            int actualprocessedNumInSequence = (int) _nextNumberInSequenceField.get(fizzBuzzService);

            String expectedValue = Integer.toString(i);
            if(i % 3 == 0 && i % 5 == 0) expectedValue="FizzBuzz";
            else if(i % 3 == 0) expectedValue="Fizz";
            else if(i % 5 == 0) expectedValue="Buzz";

            HashMap<Integer,String> result = (HashMap<Integer,String>) responseObject.get(FizzBuzzResponseKey.result);
            String actualValue = result.get(i);
            
            // assert
            assertEquals(i, actualprocessedNumInSequence);
            assertNotNull(result);
            assertNotNull(actualValue);
            assertEquals(expectedValue, actualValue);
        }

    }

}
