package Prototype.strategypattern;

public class DirectServiceStrategy implements DataFetchStrategy {
    private ServiceClient serviceClient;

    public DirectServiceStrategy(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    @Override
    public Data fetchData(RequestParams requestParams) {
        return serviceClient.callService(requestParams);
    }
}

