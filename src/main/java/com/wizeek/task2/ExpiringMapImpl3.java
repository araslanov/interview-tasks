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
        ExpirableValue expirableValue = new ExpirableValue(value);
        valueMap.put(key, expirableValue);
        executor.schedule(() -> {
            valueMap.merge(key, expirableValue, (ev1, ev2) -> ev1 == ev2 ? null : ev1);
        }, timeToLive, TimeUnit.MILLISECONDS);
    }

    @Override
    public Integer get(int key) {
        return valueMap.getOrDefault(key, DEFAULT).value;
    }

    private static class ExpirableValue {
        Integer value;

        ExpirableValue() {
        }

        ExpirableValue(Integer value) {
            this.value = value;
        }

        @Override
        public final boolean equals(Object obj) {
            return this == obj;
        }
    }
}
