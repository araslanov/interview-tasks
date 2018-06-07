package com.wizeek.task2;

/**
 * Created by Artur on 6/7/2018.
 */
public interface ExpiringMap {
    void put(int key, int value, long timeToLive);
    Integer get(int key);
}
