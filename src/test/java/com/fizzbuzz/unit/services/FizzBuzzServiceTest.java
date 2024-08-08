package com.fizzbuzz.unit.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fizzbuzz.enums.FizzBuzzResponseKey;
import com.fizzbuzz.services.FizzBuzzService;

@SpringBootTest
@DisplayName("FizzBuzzService Tests")
public class FizzBuzzServiceTest {

    @Autowired
    FizzBuzzService fizzBuzzService;

    private final static String EXPECTED_RESULT_FILE = "src/test/resources/expectedResults.txt";

    private static ArrayList<String> expectedResults = new ArrayList<>();

    @BeforeAll
    static void setExpectedResult(){
        try(BufferedReader reader = new BufferedReader(new FileReader(EXPECTED_RESULT_FILE))){
            String line="";
            while((line = reader.readLine())!=null){
                expectedResults.add(line);
            }
        }
        catch(Exception e){
            // fail the tests if any error in file reading
            fail("FAILING all the tests due to following error: "+e.getMessage());
        }
    }

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
        for(String expectedValue : expectedResults){
            String actualValue = result.get(expectedResults.indexOf(expectedValue)+1);
            assertNotNull(actualValue);
            assertEquals(expectedValue, actualValue);
        }
    }

    @Test()
    @DisplayName("processNextNumInSequence -> assert if the function is generating expected result for next number in sequence of 1-100")
    void processNextNumInSequenceTest() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{

        // assert all the numbers in the result set 1-100
        for (int i=1;i<=100;i++){

            //act
            HashMap<FizzBuzzResponseKey,Object> responseObject = fizzBuzzService.processNextNumInSequence();

            HashMap<Integer,String> result = (HashMap<Integer,String>) responseObject.get(FizzBuzzResponseKey.result);
            String actualValue = result.get(i);
            
            // assert
            assertNotNull(result);
            assertNotNull(actualValue);
            assertEquals(expectedResults.get(i-1).toString(), actualValue);
        }
    }


    @Test
    @DisplayName("resetSequenceTest -> assert if the sequence is reseted to 1")
    void resetSequenceTest(){
        int expectedNum = 1;

        // act
        fizzBuzzService.setNextNumberInSequence(1);

        int actualNum = fizzBuzzService.getNextNumberInSequence();

        // assert
        assertEquals(expectedNum, actualNum);
    }

    @ParameterizedTest
    @CsvSource({
        "15, FizzBuzz",
        "9, Fizz",
        "5, Buzz",
        "23, 23",
        "-1, number cannot be negative"
    })
    @DisplayName("_checkIsMultipleTest -> assert if checkIsMultiple() is returning correct string result")
	void _checkIsMultipleTest(int number, String expectedResult) throws Exception{
        Class<FizzBuzzService> _class = FizzBuzzService.class;
        Method checkIsMultiple = _class.getDeclaredMethod("checkIsMultiple", int.class);
        checkIsMultiple.setAccessible(true);
        try{
            String actualResult = (String) checkIsMultiple.invoke(fizzBuzzService, number);
            assertEquals(expectedResult, actualResult);
        }catch(Exception e){
            assertEquals(expectedResult, e.getCause().getMessage());
        }
	}
}
