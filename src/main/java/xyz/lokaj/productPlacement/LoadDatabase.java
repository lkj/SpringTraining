package xyz.lokaj.productPlacement;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.lokaj.productPlacement.controller.ProductsRepository;
import xyz.lokaj.productPlacement.model.Product;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ProductsRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Product("Kapusta", new BigDecimal(5))));
      log.info("Preloading " + repository.save(new Product("Salata", new BigDecimal(10))));
    };
  }
}
