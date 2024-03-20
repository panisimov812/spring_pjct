package com.panem.panem_backend;

import com.panem.panem_backend.models.UserCounter;
import com.panem.panem_backend.repository.UserCounterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PanemBackendApplication {


    private static final Logger log = LoggerFactory.getLogger(PanemBackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PanemBackendApplication.class, args);
    }

    //бин чтобы включить логирование
    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("application");
    }


}
