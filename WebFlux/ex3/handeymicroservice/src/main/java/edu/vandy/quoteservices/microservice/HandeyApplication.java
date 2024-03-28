package edu.vandy.quoteservices.microservice;

import edu.vandy.quoteservices.utils.BaseApplication;
import edu.vandy.quoteservices.common.Quote;
import edu.vandy.quoteservices.repository.HandeyQuoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import java.util.List;

/**
 * This class provides the entry point into the Spring WebMVC-based
 * version of the Handey quote microservice.
 *
 * The {@code @SpringBootApplication} annotation enables apps to use
 * autoconfiguration, component scan, and to define extra
 * configurations on their "application" class.
 *
 * The {@code @EntityScan} annotation is used when entity
 * classes are not placed in the main application package
 * or its sub-packages.
 *
 * The {@code @EnableR2dvcRepositories} annotation activates
 * reactive relational repositories using R2DBC.
 */
@SpringBootApplication
@EntityScan(basePackageClasses =
    {Quote.class})
@EnableR2dbcRepositories(basePackageClasses =
    {HandeyQuoteRepository.class})
public class HandeyApplication 
       extends BaseApplication {
    /**
     * The static main() entry point runs this Spring application.
     */
    public static void main(String[] args) {
        // Call BaseApplication helper method to build and run this
        // microservice.
        run(HandeyApplication.class, args);
    }

    // @Bean
    public CommandLineRunner demo(HandeyQuoteRepository repository) {
        return args -> {
            repository.findAllById(List.of(1, 2))
                      .subscribe(it -> System.out.println(it.quote));
        };
    }
}
