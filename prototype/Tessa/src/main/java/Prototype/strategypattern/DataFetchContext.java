package Prototype.strategypattern;

public class DataFetchContext {
    private DataFetchStrategy currentStrategy;

    public void setStrategy(DataFetchStrategy strategy) {
        this.currentStrategy = strategy;
    }

    public Data fetchData(RequestParams requestParams) {
        return currentStrategy.fetchData(requestParams);
    }
}

