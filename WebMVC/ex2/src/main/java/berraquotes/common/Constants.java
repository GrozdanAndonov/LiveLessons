package berraquotes.common;

/**
 * This class centralizes all constants used by the Berra
 * microservices.
 */
public class Constants {
    public static final String SERVER_BASE_URL = "http://localhost:9102";

    /**
     * All supported HTTP request endpoints.
     */
    public static class EndPoint {
        public static final String GET_ALL_QUOTES = "allQuotes";
        public static final String GET_QUOTES = "quotes";
        public static final String GET_SEARCH = "search";
        public static final String GET_SEARCHES = "searches";
        public static final String SEARCH_QUERY = "/{query}";

        /**
         * Supported HTTP request parameters identifiers.
         */
        public static class Params {
            public static final String QUOTE_IDS = "quoteIds";
            public static final String STRATEGY = "strategy";
            public static final String QUERIES = "queries";
        }
    }

    public static class Strategies {
        public static final int STRUCTURED_CONCURRENCY = 0;
        public static final int PARALLEL_STREAMS = 1;
        public static final int PARALLEL_STREAMS_REGEX = 2;
        public static final int SEQUENTIAL_STREAMS = 3;
    }
}
