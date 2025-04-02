package Prototype.strategypattern;

import java.util.*;
import java.time.Duration;

public class StrategyPatternDemo {

    public static void main(String[] args) {
        ServiceClient serviceClient = new MockServiceClient();
        CacheProvider cacheProvider = new MockCacheProvider();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("userId", "12345");
        RequestParams requestParams = new RequestParams(DataType.USER_PROFILE, queryParams);

        DirectServiceStrategy directStrategy = new DirectServiceStrategy(serviceClient);
        CacheStrategy cacheStrategy = new CacheStrategy(cacheProvider, directStrategy, getTtlConfig());

        StrategySelector strategySelector = new StrategySelector(serviceClient, directStrategy, cacheStrategy);

        DataFetchContext context = new DataFetchContext();
        DataFetchStrategy selectedStrategy = strategySelector.selectStrategy();
        context.setStrategy(selectedStrategy);

        Data fetchedData = context.fetchData(requestParams);
        System.out.println("Fetched Data: " + fetchedData);
    }

    private static Map<DataType, Duration> getTtlConfig() {
        Map<DataType, Duration> ttlConfig = new HashMap<>();
        ttlConfig.put(DataType.USER_PROFILE, Duration.ofMinutes(5));
        ttlConfig.put(DataType.PRODUCT_CATALOG, Duration.ofMinutes(10));
        return ttlConfig;
    }

    // Mock ServiceClient to simulate service availability and fetching data
    static class MockServiceClient extends ServiceClient {
        @Override
        public Data callService(RequestParams requestParams) {
            // Simulating service call returning actual data
            System.out.println("Calling external service...");
            return new Data("Service data for User Profile: UserID 12345");
        }

        @Override
        public boolean isServiceAvailable() {
            // Simulating service availability (return false to use cache strategy)
            return false; // Change to `true` to simulate a successful service call
        }
    }

    // Mock CacheProvider to simulate cache behavior
    static class MockCacheProvider extends CacheProvider {
        @Override
        public Optional<Data> get(String key) {
            System.out.println("Cache miss...");
            return Optional.empty(); // Simulating a cache miss
        }

        @Override
        public void put(String key, Data data, Duration ttl) {
            // Simulate storing data in the cache
            System.out.println("Storing data in cache with TTL: " + ttl);
        }

        @Override
        public void invalidate(String key) {
            // Simulate invalidating cache
            System.out.println("Invalidating cache for key: " + key);
        }
    }
}


