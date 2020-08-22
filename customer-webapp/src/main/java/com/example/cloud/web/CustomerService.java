package com.example.cloud.web;

import com.example.cloud.web.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final WebClient webClient;

    public CustomerService(Environment environment, WebClient.Builder webClientBuilder) {
        String gatewayServer = environment.getRequiredProperty("gateway.server");
        this.webClient = webClientBuilder.baseUrl(gatewayServer).build();
    }

    public Customer[] getCustomers() {
        logger.debug("In getCustomers");
        return this.webClient.get().uri("/customer-service/customers")
                .retrieve().bodyToMono(Customer[].class)
                .doOnError(throwable -> logger.error(throwable.getMessage(), throwable))
                .onErrorReturn(new Customer[]{})
                .block();
    }

    public Customer getCustomer(long id) {
        logger.debug("In getCustomer with {}", id);
        return this.webClient.get().uri("/customer-service/customers/{id}", id)
                .retrieve().bodyToMono(Customer.class)
                .doOnError(throwable -> logger.error(throwable.getMessage(), throwable))
                .block();
    }

    public Customer create(Customer customer) {
        logger.debug("In create with {}", customer);
        customer.createContactList();

        Customer customerResponse = this.webClient.post()
                .uri("/customer-service/customers")
                .body(Mono.just(customer), Customer.class)
                .retrieve()
                .bodyToMono(Customer.class)
                .doOnError(throwable -> logger.error(throwable.getMessage(), throwable))
                .block();

        logger.debug("Out create with {}", customerResponse);
        return customerResponse;
    }
}