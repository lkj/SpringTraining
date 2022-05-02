package xyz.lokaj.productPlacement;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ProductRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Product("Bilbo Baggins", new BigDecimal(100))));
      log.info("Preloading " + repository.save(new Product("Frodo Baggins", new BigDecimal(2000))));
    };
  }
}
