
# FizzBuzz App

Classic children’s game “Fizz buzz”. This is a microservice which loops through a sequence of numbers ranging from 1-100 and prints the following result:

- if a number is multiple of 3 then print "Fizz"
- if a number is multiple of 5 then print "Buzz"
- if a number is multiple of both 3 and 5 then print "FizzBuzz"
- otherwise print the number it self

### Quick Links

- [API Reference](#API-Reference)
- [Developer doc - local project setup and execution](#Developer-Documentation)
- [Live Deployment Url](#Live-Deployment)

## API Reference

#### Get the result response for sequence of 1-100 based on multiple of 5 or 3

```http
  GET /api/v1/getFizzBuzzForSequence
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `NULL`    | `N/A`    | N/A                        |

[Sample Response Object](#getFizzBuzzForSequence)

DESCRIPTION: Return the response object containing the array of result for all the numbers in 1-100 

#### Get item

```http
  GET /api/v1/getNextInSequence
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `NULL`    | `N/A`    | N/A                        |

[Sample Response Object](#getNextInSequence)

DESCRIPTION: Return the response object containing the  result for next number in 1-100 


## Developer Documentation

### Prerequisits
- Java 17
- Maven version 3.9.*
- Any JVM supporting IDE (IntelliJ, VS Code etc.)

### Local Setup
- clone the project in your local: ```https://github.com/sauravsehgal24/FizzBuzz.git```
- open the project in your preferred IDE (Note: I developed this project in VS Code)
- install maven dependencies in your local maven repository: ```mvn clean install```
- NOTE: the above command will also run the unit and E2E tests
- Run the src/main/java/com/fizzbuzz/FizzBuzz.java file and make sure that the server is started on port 8080
- Navigate to POSTMAN or your Browser and test the API: http://localhost:8080/api/v1/getFizzBuzzForSequence
- Navigate to POSTMAN or your Browser and test the API: http://localhost:8080/api/v1/getNextInSequence


## Live Deployment

This microservice is currently deployed on 


### Sample Responses

#### getFizzBuzzForSequence

```http
  GET /api/v1/getFizzBuzzForSequence
```

```json
{
    "httpStatusCode": "OK",
    "result": {
        "1": "1",
        "2": "2",
        "3": "Fizz",
        "4": "4",
        "5": "Buzz",
        "6": "Fizz",
        "7": "7",
        "8": "8",
        "9": "Fizz",
        "10": "Buzz",
        "11": "11",
        "12": "Fizz",
        "13": "13",
        "14": "14",
        "15": "FizzBuzz",
        "16": "16",
        "17": "17",
        "18": "Fizz",
        "19": "19",
        "20": "Buzz",
        "21": "Fizz",
        "22": "22",
        "23": "23",
        "24": "Fizz",
        "25": "Buzz",
        "26": "26",
        "27": "Fizz",
        "28": "28",
        "29": "29",
        "30": "FizzBuzz",
        "31": "31",
        "32": "32",
        "33": "Fizz",
        "34": "34",
        "35": "Buzz",
        "36": "Fizz",
        "37": "37",
        "38": "38",
        "39": "Fizz",
        "40": "Buzz",
        "41": "41",
        "42": "Fizz",
        "43": "43",
        "44": "44",
        "45": "FizzBuzz",
        "46": "46",
        "47": "47",
        "48": "Fizz",
        "49": "49",
        "50": "Buzz",
        "51": "Fizz",
        "52": "52",
        "53": "53",
        "54": "Fizz",
        "55": "Buzz",
        "56": "56",
        "57": "Fizz",
        "58": "58",
        "59": "59",
        "60": "FizzBuzz",
        "61": "61",
        "62": "62",
        "63": "Fizz",
        "64": "64",
        "65": "Buzz",
        "66": "Fizz",
        "67": "67",
        "68": "68",
        "69": "Fizz",
        "70": "Buzz",
        "71": "71",
        "72": "Fizz",
        "73": "73",
        "74": "74",
        "75": "FizzBuzz",
        "76": "76",
        "77": "77",
        "78": "Fizz",
        "79": "79",
        "80": "Buzz",
        "81": "Fizz",
        "82": "82",
        "83": "83",
        "84": "Fizz",
        "85": "Buzz",
        "86": "86",
        "87": "Fizz",
        "88": "88",
        "89": "89",
        "90": "FizzBuzz",
        "91": "91",
        "92": "92",
        "93": "Fizz",
        "94": "94",
        "95": "Buzz",
        "96": "Fizz",
        "97": "97",
        "98": "98",
        "99": "Fizz",
        "100": "Buzz"
    },
    "message": "Successfuly processed the sequence!"
}
```

#### getNextInSequence

```http
  GET /api/v1/getNextInSequence
```

```json 
{
    "httpStatusCode": "OK",
    "result": {
        "1": "1"
    },
    "message": "Successfuly processed the number in sequence!"
}
```