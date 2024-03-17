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

//    @Bean
//    public CommandLineRunner demo12(UserCounterRepository repository) {
//        return (args) -> {
//            // save a few customers
//            repository.save(new UserCounter("4100315004057239", "Bauer",12));

//
//
//            // fetch an individual customer by ID
//            repository.updateBanknoteValueByAccount("4100315004013576", 121);
//            log.info("обновили банкноты");
//            log.info("--------------------------------");
//            log.info("");
//
//            repository.updateLevelValueByAccount ("4100315004013576", 5);
//            log.info("обновили уровень");
//            log.info("--------------------------------");
//            log.info("");
//
//            repository.updateCoinValueByAccount ("4100315004013576", 5);
//            log.info("обновили монет");
//            log.info("--------------------------------");
//            log.info("");
//
//            UserCounter counter = repository.findById(136023L);
//            log.info("Customer found with findById(1L):");
//            log.info("--------------------------------");
//            log.info(counter.toString());
//            log.info("");
//
//            UserCounter energy = repository.findByAccountAndName("4100315004013576","YV2021.Energy.Counter");
//            log.info("Customer found with :");
//            log.info("--------------------------------");
//            log.info(energy.toString());
//            log.info("");



//        };
//    }

    //бин чтобы включить логирование
    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("application");
    }


}
