package com.s1gawron.technologiechmurowe;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
@Log4j2(topic = "TechnologieChmuroweAppInfo")
public class TechnologieChmuroweApplication {

    @Value("${server.port}")
    private String applicationPort;

    public static void main(String[] args) {
        SpringApplication.run(TechnologieChmuroweApplication.class, args);
    }

    @Bean
    public void printInfo() {
        log.info("Data uruchomienia kontenera: {}", LocalDateTime.now());
        log.info("Autor: Sebastian Gawron");
        log.info("Port na ktorym nasluchuje aplikacja: {}", applicationPort);
    }

}
