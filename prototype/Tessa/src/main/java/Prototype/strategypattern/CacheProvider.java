package Prototype.strategypattern;

import java.util.Optional;
import java.time.Duration;

public class CacheProvider {
    public Optional<Data> get(String key) {
        return Optional.empty();
    }

    public void put(String key, Data data, Duration ttl) {
    }

    public void invalidate(String key) {
    }
}

