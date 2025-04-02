package Prototype.strategypattern;

public class StrategySelector {
    private ServiceClient serviceClient;
    private DirectServiceStrategy directStrategy;
    private CacheStrategy cacheStrategy;

    public StrategySelector(ServiceClient serviceClient, DirectServiceStrategy directStrategy, CacheStrategy cacheStrategy) {
        this.serviceClient = serviceClient;
        this.directStrategy = directStrategy;
        this.cacheStrategy = cacheStrategy;
    }

    public DataFetchStrategy selectStrategy() {
        if (serviceClient.isServiceAvailable()) {
            return directStrategy;
        } else {
            return cacheStrategy;
        }
    }
}

