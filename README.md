# Spring Cloud Microservice 
## What was build
Spring Cloud Microservices using external configurations, discover and consume services, and isolate from failure with the circuit breaker pattern

- Actuator - Expose operational information about the running application
- Swagger2 - Documentation from the API [Swagger.io](https://swagger.io)
- Eureka - REST based Service Discovery [Eureka Netflix](https://github.com/Netflix/eureka/wiki/Eureka-at-a-glance)
- Feign - Declarative REST Client [Spring Cloud OpenFeign](https://cloud.spring.io/spring-cloud-openfeign/single/spring-cloud-openfeign.html)

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
Now that the Config server is running, visit http://localhost:8888/personservices/default to see the exposed properties configuration

## Eureka server
Service discovery for Microservices

Run eureka-server
```
cd eureka-server
mvn spring-boot:run
```

## Test Eureka server
Now that the server is running, visit http://localhost:8761 to see the list of exposed applications registered with Eureka

## Person services
A set of services to provide data access to Person

Run person-services API
```
cd person-services
mvn spring-boot:run
```

## Test Person services
Now that the API is running, visit http://localhost:8001 to see the API documentation using Swagger2 with the list of exposed endpoints

## Person business services
A set of services to consume Person services from Eureka Server with Feign 

Run person-business-services 
```
cd person-business-services
mvn spring-boot:run
```

## Test Person business  services
Now that the API is running, visit http://localhost:8002 to see the API documentation using Swagger2 with the list of exposed endpoints

#Microservices Overview
![Microservices Overiew](https://github.com/diogo-santos/spring-cloud-ms/blob/master/service-design.png)
