package com.example.hellosrping

import groovyjarjarpicocli.CommandLine.IDefaultValueProvider
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

class HelloSrpingApplication {

    static void main(String[] args) {
        SpringApplication.run(HelloSrpingApplication, args)

    }
    @GetMapping("/")
        public String hello(@RequestParam(value = "name", defaultValue = "Oscar") String name) {
        return String.format("Hello %s!", name);
    }
    @GetMapping("/holaMundoSpring")
        public String hola(@RequestParam(value = "name", defaultValue = "MundoSpring") String name){
            return String.format("Hello %s!", name);
        }
    }


