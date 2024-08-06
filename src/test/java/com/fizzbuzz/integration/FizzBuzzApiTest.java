package com.fizzbuzz.integration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FizzBuzzApiTest {

    @LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

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
	void getFizzBuzzForSequenceTest(){

        HashMap<String,Object> res = new HashMap<>();
        res = restTemplate.getForObject("http://localhost:" + port + "/api/v1/getFizzBuzzForSequence", res.getClass());
        // assert api response
        assertNotNull(res.get("message"));
        assertNotNull(res.get("httpStatusCode"));
        assertNotNull(res.get("result"));

        String actualMessage = res.get("message").toString();
        String actualHttpStatusCode = res.get("httpStatusCode").toString();
        HashMap<String,String> actualResult = (HashMap<String,String>) res.get("result");
        assertEquals("Successfuly processed the sequence!",actualMessage);
        assertEquals("OK",actualHttpStatusCode);

        // assert the response result
        for(int i=1; i<=100; i++){
            String expectedValue = expectedResults.get(i-1);
            String actualValue = actualResult.get(i+"");

            assertNotNull(actualValue);
            assertEquals(expectedValue, actualValue);
        }
	}

    @Test
	void getNextInSequenceTest(){
        HashMap<String,Object> res = new HashMap<>();
        for(int i=1; i<=100; i++){
            res = restTemplate.getForObject("http://localhost:" + port + "/api/v1/next", res.getClass());
            // assert api response
            assertNotNull(res.get("message"));
            assertNotNull(res.get("httpStatusCode"));
            assertNotNull(res.get("result"));

            String actualMessage = res.get("message").toString();
            String actualHttpStatusCode = res.get("httpStatusCode").toString();
            HashMap<String,String> actualResult = (HashMap<String,String>) res.get("result");

            assertEquals("Successfuly processed the number in sequence!",actualMessage);
            assertEquals("OK",actualHttpStatusCode);

            String expectedValue = expectedResults.get(i-1);
            String actualValue = actualResult.get(i+"");
            assertNotNull(actualValue);
            assertEquals(expectedValue, actualValue);
        }
	}

    @Test
	void resetSequence(){
        HashMap<String,Object> res = new HashMap<>();

        // reset the sequence
        restTemplate.getForObject("http://localhost:" + port + "/api/v1/reset", String.class);

        // retrieve the result for current number in sequence 
        res = restTemplate.getForObject("http://localhost:" + port + "/api/v1/next", res.getClass());
        HashMap<String,String>  result = (HashMap<String,String>) res.get("result");

        // assert that the current number in sequence is 1 
        assertNotNull(result);
        assertNotNull(result.get("1"));
	}
}
