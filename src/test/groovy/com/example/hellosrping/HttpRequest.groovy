package com.example.hellosrping

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import static org.assertj.core.api.Assertions.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HttpRequest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void canAdd() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/suma?a=4&b=6", String.class))
        .isEqualTo("10.0");
    }

}
