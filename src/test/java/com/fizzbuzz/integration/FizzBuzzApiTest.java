package com.fizzbuzz.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.HashMap;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FizzBuzzApiTest {

    @LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

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
            String expectedValue = Integer.toString(i);
            if(i % 3 == 0 && i % 5 == 0) expectedValue="FizzBuzz";
            else if(i % 3 == 0) expectedValue="Fizz";
            else if(i % 5 == 0) expectedValue="Buzz";

            String actualValue = actualResult.get(i+"");

            assertNotNull(actualValue);
            assertEquals(expectedValue, actualValue);
        }
	}

    @Test
	void getNextInSequence(){
        HashMap<String,Object> res = new HashMap<>();
        for(int i=1; i<=100; i++){
            res = restTemplate.getForObject("http://localhost:" + port + "/api/v1/getNextInSequence", res.getClass());
            // assert api response
            assertNotNull(res.get("message"));
            assertNotNull(res.get("httpStatusCode"));
            assertNotNull(res.get("result"));

            String actualMessage = res.get("message").toString();
            String actualHttpStatusCode = res.get("httpStatusCode").toString();
            HashMap<String,String> actualResult = (HashMap<String,String>) res.get("result");

            assertEquals("Successfuly processed the number in sequence!",actualMessage);
            assertEquals("OK",actualHttpStatusCode);


            String expectedValue = Integer.toString(i);
            if(i % 3 == 0 && i % 5 == 0) expectedValue="FizzBuzz";
            else if(i % 3 == 0) expectedValue="Fizz";
            else if(i % 5 == 0) expectedValue="Buzz";

            String actualValue = actualResult.get(i+"");
            assertNotNull(actualValue);
            assertEquals(expectedValue, actualValue);
        }
	}
}
