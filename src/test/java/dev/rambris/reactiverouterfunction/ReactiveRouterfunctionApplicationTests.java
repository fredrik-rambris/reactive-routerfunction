package dev.rambris.reactiverouterfunction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest({RouterConfig.class, BackendService.class})
class ReactiveRouterfunctionApplicationTests {
    @Autowired
    private WebTestClient webClient;

    @Test
    void testRouter() {
        webClient.get().uri("/test").exchange().expectStatus().isOk();
    }
}
