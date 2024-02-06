package dev.rambris.reactiverouterfunction;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@Service
public class BackendService {
    private final Random random = new Random();

    public Mono<Boolean> doesTypeSlugExist(String typeSlug) {
        // Query database or other service to check if typeSlug exists, probably cached
        return Mono.just(true).delayElement(Duration.ofMillis(500));
    }

    public Mono<String> fetchContent(String typeSlug) {
        // Query database or other service to fetch content
        return Mono.just("Fetched from backend somehow").delayElement(Duration.ofMillis(500));
    }
}
