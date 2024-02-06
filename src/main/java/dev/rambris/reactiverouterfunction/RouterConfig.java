package dev.rambris.reactiverouterfunction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Configuration
public class RouterConfig {
    private final BackendService svc;

    public RouterConfig(BackendService svc) {
        this.svc = svc;
    }

    @Bean
    public RouterFunction<ServerResponse> myRoutes() {
        return RouterFunctions.route()
                .GET("{typeSlug:^[a-z_-]+$}", this::predicate, this::handler)
                .GET("{typeSlug:^[a-z_-]+$}/", this::predicate, this::handler)
                .build();
    }

    private boolean predicate(ServerRequest serverRequest) {
            var typeSlug = serverRequest.pathVariable("typeSlug");
            return Boolean.TRUE.equals(svc.doesTypeSlugExist(typeSlug).block());

    }

    private Mono<ServerResponse> handler(ServerRequest serverRequest) {
        return svc.fetchContent(serverRequest.pathVariable("typeSlug"))
                .flatMap(content -> ServerResponse.ok().bodyValue(content));
    }
}
