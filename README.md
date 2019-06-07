# Spring Cloud Microservice 
## What was build
Spring Cloud Microservices using external configurations, discover and consume services, and isolate from failure with the circuit breaker pattern

- Swagger2 - Documentation from the API [swagger.io](https://swagger.io)

## What you'll need

Java 1.8

Maven 3.0+

## Instructions
Import the project from GitHub

## Config Server
Configuration server for Spring Cloud

Run config-server
```
cd config-server
mvn spring-boot:run
```

##Person services
A set of services to provide data access to Person

Run person-services API
```
cd person-services
mvn spring-boot:run
```

## Test Person services
Now that the API is running, visit http://localhost:8001 to see the API documentation with the list of exposed endpoints
