package com.wizeek.task2;

import java.util.concurrent.*;

/**
 * Created by Artur on 6/7/2018.
 */
public class ExpiringMapImpl3 implements ExpiringMap {

    private static final ExpirableValue DEFAULT = new ExpirableValue();
    private ConcurrentMap<Integer, ExpirableValue> valueMap = new ConcurrentHashMap<>();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void put(int key, int value, long timeToLive) {
        long timeToDie = System.currentTimeMillis() + timeToLive;
        ExpirableValue expirableValue = new ExpirableValue(value, timeToDie);
        valueMap.put(key, expirableValue);
        executor.schedule(() -> {
            valueMap.replace(key, expirableValue, DEFAULT);
        }, timeToLive, TimeUnit.MILLISECONDS);
    }

    @Override
    public Integer get(int key) {
        return valueMap.getOrDefault(key, DEFAULT).value;
    }

    private static class ExpirableValue {
        Integer value;
        long expirationTime;

        ExpirableValue() {}

        ExpirableValue(Integer value, long expirationTime) {
            this.value = value;
            this.expirationTime = expirationTime;
        }
    }
}
