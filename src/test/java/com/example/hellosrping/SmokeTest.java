package com.example.hellosrping;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {
    @Test
    public void contextLoads() {
        //asegurarnos de que la clase helloSpring app existe.
        Assertions.assertThat(controller).isNotNull();

    }

    @Autowired
    private HelloSrpingApplication controller;
}
