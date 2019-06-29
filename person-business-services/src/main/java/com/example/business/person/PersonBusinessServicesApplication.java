package com.example.business.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class PersonBusinessServicesApplication {
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("PersonBusiness")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.business.person"))
				.paths(any())
				.build()
				.apiInfo(new ApiInfo("Person Business Services",
						"A set of services to provide business services for the Person domain", "0.0.1", null,
						new Contact("Diogo S.", "http://github.com/diogo-santos", null),null, null));
	}

	public static void main(String[] args) {
		SpringApplication.run(PersonBusinessServicesApplication.class, args);
	}
}