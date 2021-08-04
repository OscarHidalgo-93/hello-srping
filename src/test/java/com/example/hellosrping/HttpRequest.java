package com.example.hellosrping;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequest {
    @Test
    public void canAdd() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/suma?a=4&b=6", String.class)).isEqualTo("10.0");
    }

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
}
