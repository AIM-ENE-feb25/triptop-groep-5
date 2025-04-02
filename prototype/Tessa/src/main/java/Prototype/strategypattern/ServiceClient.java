package Prototype.strategypattern;

public class ServiceClient {
    public Data callService(RequestParams requestParams) {
        return new Data("Service data for User Profile: UserID 12345");
    }

    public boolean isServiceAvailable() {
        return true;
    }
}

