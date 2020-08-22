# Spring Cloud Microservice
![Microservices Overview](architecture-design.png)

Spring Cloud Microservices using configuration properties server, Eureka service discovery, Zuul gateway server, and isolate from failure with Hystrix

- [ ] Actuator - Expose operational information about the running application
- [ ] [Swagger.io](https://swagger.io) - Documentation from the API
- [ ] [Eureka Netflix](https://github.com/Netflix/eureka/wiki/Eureka-at-a-glance) - REST based Service Discovery 
- [ ] [Spring Cloud OpenFeign](https://cloud.spring.io/spring-cloud-openfeign/single/spring-cloud-openfeign.html) - Declarative REST Client
- [ ] Hystrix - Circuit Break pattern
- [ ] Hystrix Dashboard
- [ ] [Zuul](https://github.com/Netflix/zuul) - Gateway Server

## Getting the project in your computer
- [ ] Java 8
- [ ] Maven 3

Import the project from GitHub

Build and Execute script to startup all services
```
mvn clean package && ./run.sh
```

## Config Server
Configuration server for Spring Cloud that provides all config properties from Git

When the Config server is up and running, visit http://localhost:8888/customerservices/default to see the exposed properties configuration

## Eureka server
Service discovery for Microservices

When the Eureka server is up and running, visit http://localhost:8761 to see the list of exposed applications registered with Eureka

## Customer services
A set of services to provide data access to Customer

When the service is up and running, visit http://localhost:8001 to see the API documentation using Swagger2 with the list of exposed endpoints

## Customer contact services
A set of services to provide data access to Contact info

When the service is up and running, visit http://localhost:8003 to see the API documentation using Swagger2 with the list of exposed endpoints

## Customer business services
A set of services to consume Customer services from Eureka Server with Feign 

When the service is up and running, visit http://localhost:8002 to see the API documentation using Swagger2 with the list of exposed endpoints

## Customer web app
Web app using Thymeleaf and Bootstrap for creating and display Customer entities in the system, Rest calls using Spring React

When the service is up and running, visit http://localhost:8080 to see the landing web page

## Hystrix Dashboard
Visit http://localhost:8002/hystrix, type in the text box: http://localhost:8002/actuator/hystrix.stream and click on "Monitor Stream"

![hystrix home](hystrix-home.png)
