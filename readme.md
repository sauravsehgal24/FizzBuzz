
# FizzBuzz App

Classic children’s game “Fizz buzz”. This is a microservice which prints the following result:

- if a number is multiple of 3 then print "Fizz"
- if a number is multiple of 5 then print "Buzz"
- if a number is multiple of both 3 and 5 then print "FizzBuzz"
- otherwise print the number it self

### Quick Links

- [API Swagger Reference](http://35.183.144.73:8080/swagger-ui/index.html#/)
- [Developer doc - local project setup and execution](#Developer-Documentation)
- [Live Deployment Url](#Live-Deployment)


## Developer Documentation

### Prerequisits
- Java 17
- Maven version 3.9.*
- Any JVM supporting IDE (IntelliJ, VS Code etc.)

### Local Setup

NOTE: If you are using intelliJ IDE then make sure that Project SDK is set to java 17 and lombok plugin is installed and annotations are enabled

- clone the project in your local: ```https://github.com/sauravsehgal24/FizzBuzz.git```
- open the project in your preferred IDE (Note: I developed this project in VS Code)
- Navigate to root folder where pom.xml is located and install maven dependencies in your local maven repository: ```mvn clean install```
- NOTE: the above command will also run the unit and E2E tests
- Run the src/main/java/com/fizzbuzz/FizzBuzz.java file and make sure that the server is started on port 8080
- Navigate to POSTMAN or your Browser and test the API: GET http://localhost:8080/api/v1/getFizzBuzzForSequence
- Navigate to POSTMAN or your Browser and test the API: GET http://localhost:8080/api/v1/next
- Navigate to POSTMAN or your Browser and test the API: GET http://localhost:8080/api/v1/reset


## Live Deployment

This microservice is currently deployed on AWS EC2 (t2 micro):

### Live Endpoints

#### getFizzBuzzForSequence

http://35.183.144.73:8080/api/v1/getFizzBuzzForSequence

#### getNextInSequence

http://35.183.144.73:8080/api/v1/next

#### resetSequence

http://35.183.144.73:8080/api/v1/reset