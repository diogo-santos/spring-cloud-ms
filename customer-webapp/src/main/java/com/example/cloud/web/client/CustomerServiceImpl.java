package com.example.cloud.web.client;

import com.example.cloud.web.domain.Customer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Customer CUSTOMER_NOT_AVAILABLE = Customer.builder().firstName("Customer data not available").build();

    private final WebClient webClient;

    public CustomerServiceImpl(Environment environment, WebClient.Builder webClientBuilder) {
        String gatewayServer = environment.getRequiredProperty("gateway.server");
        this.webClient = webClientBuilder.baseUrl(gatewayServer).build();
    }

    public Customer[] findAll() {
        return this.webClient.get().uri("/customer-service/customers")
                .retrieve().bodyToMono(Customer[].class)
                .onErrorReturn(new Customer[]{CUSTOMER_NOT_AVAILABLE})
                .block();
    }

    public Customer findById(long id) {
        return this.webClient.get().uri("/customer-service/customers/{id}", id)
                .retrieve().bodyToMono(Customer.class)
                .onErrorReturn(CUSTOMER_NOT_AVAILABLE)
                .block();
    }

    public Customer create(Customer customer) {
        return this.webClient.post().uri("/customer-service/customers")
                .body(Mono.just(customer), Customer.class)
                .retrieve()
                .bodyToMono(Customer.class)
                .onErrorReturn(CUSTOMER_NOT_AVAILABLE)
                .block();
    }
}