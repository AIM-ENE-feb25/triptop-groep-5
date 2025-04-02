package Prototype.strategypattern;

import java.util.Map;
import java.util.Optional;
import java.time.Duration;

public class CacheStrategy implements DataFetchStrategy {
    private CacheProvider cache;
    private DataFetchStrategy fallbackStrategy;
    private Map<DataType, Duration> ttlConfig;

    public CacheStrategy(CacheProvider cache, DataFetchStrategy fallbackStrategy, Map<DataType, Duration> ttlConfig) {
        this.cache = cache;
        this.fallbackStrategy = fallbackStrategy;
        this.ttlConfig = ttlConfig;
    }

    @Override
    public Data fetchData(RequestParams requestParams) {
        String cacheKey = getCacheKey(requestParams);
        Optional<Data> cachedData = cache.get(cacheKey);

        if (cachedData.isPresent()) {
            return cachedData.get();
        } else {
            Data fetchedData = fallbackStrategy.fetchData(requestParams);
            storeInCache(cacheKey, fetchedData, requestParams.getDataType());
            return fetchedData;
        }
    }

    private void storeInCache(String key, Data data, DataType dataType) {
        Duration ttl = ttlConfig.get(dataType);
        cache.put(key, data, ttl);
    }

    private String getCacheKey(RequestParams requestParams) {
        return requestParams.getQueryParameters().toString();
    }
}

