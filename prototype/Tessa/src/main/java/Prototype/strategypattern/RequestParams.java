package Prototype.strategypattern;

import java.util.Map;

public class RequestParams {
    private DataType dataType;
    private Map<String, String> queryParameters;

    public RequestParams(DataType dataType, Map<String, String> queryParameters) {
        this.dataType = dataType;
        this.queryParameters = queryParameters;
    }

    public DataType getDataType() {
        return dataType;
    }

    public Map<String, String> getQueryParameters() {
        return queryParameters;
    }
}

