package edu.vandy.quoteservices.client;

import edu.vandy.quoteservices.common.Quote;
import edu.vandy.quoteservices.utils.CallUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class is a proxy to the {@code GatewayApplication} API gateway
 * and its {@code GatewayController} that use an automatically-generated
 * Retrofit API class.
 */
@Component
public class QuoteProxy {
    /**
     * Create an instance of the {@link QuoteAPI} Retrofit client,
     * which is then used to make HTTP requests synchronously to the
     * {@code GatewayApplication} RESTful microservice.
     */
    @Autowired
    QuoteAPI mQuoteAPI;

    /**
     * Get a {@link List} containing the requested quotes.
     *
     * @param route The microservice that performs the request
     * @return An {@link List} containing all the {@link Quote}
     *         objects
     */
    public List<Quote> getAllQuotes(String route) {
        return CallUtils
            // Execute the getAllQuotes() retrofit API method.
            .executeCall(mQuoteAPI.getAllQuotes(route));
    }

    /**
     * Get a {@link Quote} corresponding to the given id.
     *
     * @param quoteId An {@link Integer} containing the given
     *                 {@code quoteId}
     * @return A {@link Quote} containing the requested {@code quoteId}
     */
    public Quote getQuote(String route,
                          Integer quoteId) {
        return CallUtils
            // Execute the getQuote() retrofit API method.
            .executeCall(mQuoteAPI.getQuote(route, quoteId));
    }

    /**
     * Get a {@link List} containing the requested quotes.
     *
     * @param route The microservice that performs the request
     * @param quoteIds A {@link List} containing the given random
     *                 {@code quoteIds}
     * @param parallel Get the {@code quoteIds} in parallel if true,
     *                 else run sequentially
     * @return An {@link List} containing the requested {@link
     *         Quote} objects
     */
    public List<Quote> postQuotes
        (String route,
         List<Integer> quoteIds,
         Boolean parallel) {
        return CallUtils
            // Execute the getQuotes() retrofit API method.
            .executeCall(mQuoteAPI
                         .postQuotes(route,
                                     quoteIds,
                                     parallel));
    }

    /**
     * Search for quotes containing any of the given {@link List} of
     * {@code queries}.
     *
     * @param route The microservice that performs the request
     * @param queries The {@link List} of {@code queries} to search
     *                for
     * @param parallel Search for the {@code queries} in parallel if
     *                 true, else run sequentially
     * @return A {@link List} of {@link Quote} objects that match the
     *         queries
     */
    public List<Quote> search
        (String route,
         List<String> queries,
         Boolean parallel) {
        return CallUtils
            // Execute the search() retrofit API method.
            .executeCall(mQuoteAPI
                         .search(route,
                                 queries,
                                 parallel));
    }

    /**
     * Search for quotes containing all the given {@link List} of
     * {@code queries}.
     *
     * @param route The microservice that performs the request
     * @param queries The {@link List} of {@code queries} to search
     *                for
     * @param parallel Search for the {@code queries} in parallel if
     *                 true, else run sequentially
     * @return A {@link List} of {@link Quote} objects that match the
     *         queries
     */
    public List<Quote> searchEx
        (String route,
         List<String> queries,
         Boolean parallel) {
        return CallUtils
            // Execute the searchEx() retrofit API method.
            .executeCall(mQuoteAPI
                         .searchEx(route, queries, parallel));
    }
}
