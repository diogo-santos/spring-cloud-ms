package com.example.cloud.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
public class CustomerBusinessServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerBusinessServicesApplication.class, args);
	}

	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.cloud.business"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(new ApiInfoBuilder()
						.title("Customer Business Services")
						.version("0.0.1")
						.description("A set of services to provide consolidated data for the Customer domain")
						.build());

	}
}