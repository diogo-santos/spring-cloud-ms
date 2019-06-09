# Spring Cloud Microservice 
## What was build
Spring Cloud Microservices using external configurations, discover and consume services, and isolate from failure with the circuit breaker pattern

- Actuator 
- Swagger2 - Documentation from the API [swagger.io](https://swagger.io)
- Eureka
- Feign

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

## Test Config server
Now that the Config server is running, visit http://localhost:8888 to see the exposed properties configuration


## Person services
A set of services to provide data access to Person

Run person-services API
```
cd person-services
mvn spring-boot:run
```

## Test Person services
Now that the API is running, visit http://localhost:8001 to see the API documentation using Swagger2 with the list of exposed endpoints

## Eureka server
Service discovery for Microservices

Run eureka-server
```
cd eureka-server
mvn spring-boot:run
```

## Test Eureka server
Now that the server is running, visit http://localhost:8761 to see the list of exposed applications registered with Eureka

## Person business services
A set of services to consume Person services from Eureka Server with Feign 

Run person-business-services 
```
cd person-business-services
mvn spring-boot:run
```

## Test Person business  services
Now that the API is running, visit http://localhost:8002 to see the API documentation using Swagger2 with the list of exposed endpoints

