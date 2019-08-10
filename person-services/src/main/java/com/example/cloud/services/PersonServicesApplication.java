package com.example.cloud.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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
public class PersonServicesApplication {
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Person")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.services"))
				.paths(any())
				.build()
				.apiInfo(new ApiInfo("Person Services",
						"A set of services to provide data access to person", "0.0.1", null,
						new Contact("Diogo S.", "http://github.com/diogo-santos", null),null, null));
	}

	public static void main(String[] args) {
		SpringApplication.run(PersonServicesApplication.class, args);
	}
}