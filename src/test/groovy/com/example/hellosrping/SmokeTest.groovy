package com.example.hellosrping

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import static org.assertj.core.api.Assertions.*

@SpringBootTest
class SmokeTest {

    @Autowired
    private HelloSrpingApplication controller

    @Test
    void contextLoads(){
        //asegurarnos de que la clase helloSpring app existe.
       assertThat(controller).isNotNull()

    }
}
