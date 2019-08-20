package com.example.cloud.web.client;

import com.example.cloud.web.domain.Person;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements PersonService {
    private static final Person PERSON_NOT_AVAILABLE = Person.builder().firstName("Person data not available").build();

    private final WebClient webClient;

    public PersonServiceImpl(Environment environment, WebClient.Builder webClientBuilder) {
        String gatewayServer = environment.getRequiredProperty("gateway.server");
        this.webClient = webClientBuilder.baseUrl(gatewayServer).build();
    }

    public Person[] findAll() {
        return this.webClient.get().uri("/person-service/person")
                .retrieve().bodyToMono(Person[].class)
                .onErrorReturn(new Person[]{PERSON_NOT_AVAILABLE})
                .block();
    }

    public Person findById(long id) {
        return this.webClient.get().uri("/person-service/person/{id}", id)
                .retrieve().bodyToMono(Person.class)
                .onErrorReturn(PERSON_NOT_AVAILABLE)
                .block();
    }

    public Person create(Person person) {
        return this.webClient.post().uri("/person-service/person")
                .body(Mono.just(person), Person.class)
                .retrieve()
                .bodyToMono(Person.class)
                .onErrorReturn(PERSON_NOT_AVAILABLE)
                .block();
    }
}