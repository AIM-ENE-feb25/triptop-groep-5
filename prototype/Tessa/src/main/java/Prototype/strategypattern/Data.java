package Prototype.strategypattern;

public class Data {
    private String content;

    public Data(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }
}


