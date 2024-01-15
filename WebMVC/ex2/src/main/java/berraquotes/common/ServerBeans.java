package berraquotes.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static berraquotes.common.Constants.SERVER_BASE_URL;

/**
 * This class contains a {@code Bean} annotation that can be injected
 * into classes using the Spring {@code @Autowired} annotation.
 */
@Configuration
public class ServerBeans {
    /**
     * @return A {@link List} of {@link Quote} objects that were
     *         stored in the {@code filePath}
     */
    // @Bean
    static public List<Quote> getQuotes() {
        try {
            // Although AtomicInteger is overkill, we use it to
            // simplify incrementing the ID in the stream below.
            AtomicInteger idCount = new AtomicInteger(0);

            // Convert the filename into a pathname.
            URI uri = ClassLoader
                .getSystemResource("berra-quotes.txt")
                .toURI();

            // Open the file and get all the bytes.
            CharSequence bytes =
                new String(Files.readAllBytes(Paths.get(uri)));

            // Return a List of ZippyQuote objects.
            return Pattern
                // Compile splitter into a regular expression (regex).
                .compile("@")

                // Use the regex to split the file into a stream of
                // strings.
                .splitAsStream(bytes)

                // Filter out any empty strings.
                .filter(((Predicate<String>) String::isEmpty).negate())

                // Create a new Quote.
                .map(quote ->
                     new Quote(idCount.getAndIncrement(),
                               quote.stripLeading()))
                
                // Collect results into a list of Quote objects.
                .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
