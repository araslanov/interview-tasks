package com.wizeek.task2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Artur_Araslanov on 6/4/2018.
 *
 * Task: implement ExpiringMap such that:
 * 12.00.00: expiringMap.put(1, 2, 3000);
 * 12.00.01: expiringMap.get(1) == 2;
 * 12.00.04: expiringMap.get(1) == null;
 *
 * A map that uses 2 approaches to clear its values based on time.
 * First one it a separate thread that periodically runs a clean up.
 * Second one is inside get() method.
 *
 * Main idea is that there is a thread that runs from time to time (with time being figured out dynamically) and cleans up outdated entries.
 * Clean up through get() method is a mechanism to cover the cases when entry was added and queried while the clean up thread was sleeping.
 *
 * A major drawback of this implementation is the need to explicitly close it to stop the clean up thread.
 * A slight quality of life improvement is the usage of AutoCloseable interface to allow for try-with-resources.
 *
 * TODO: throw exceptions from any public method if they are called after the map was closed
 */
public class ExpiringMap implements AutoCloseable {
    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("hh:mm:ss.SSS");
    private Map<Integer, ExpirableValue> map = new HashMap<>();
    private Map<Integer, ExpirableValue> newEntries = new HashMap<>();
    private TreeMap<Long, Integer> expirationMap = new TreeMap<>();
    private long nextClearTime = 0;
    private boolean runCleanUpThread = true;

    public ExpiringMap() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                while (runCleanUpThread || !expirationMap.isEmpty()) {
                    System.out.println(DATE_FORMAT.format(new Date()) + ". Starting clean up");
                    synchronized (this) {
                        long currentTime = System.currentTimeMillis();
                        nextClearTime = Long.MAX_VALUE;

                        Map.Entry<Long, Integer> currentDeletion = expirationMap.pollFirstEntry();
                        if (currentDeletion != null) {
                            System.out.println(DATE_FORMAT.format(new Date()) + ". Clearing " + currentDeletion.getValue() + " by thread");
                            remove(currentDeletion.getValue());
                        }

                        for (Map.Entry<Integer, ExpirableValue> entry : newEntries.entrySet()) {
                            long expirationTime = entry.getValue().expirationTime;
                            if (expirationTime <= currentTime) {
                                System.out.println(DATE_FORMAT.format(new Date()) + ". Clearing " + entry.getKey() + " by thread");
                                remove(entry.getKey());
                            } else {
                                expirationMap.put(expirationTime, entry.getKey());
                                nextClearTime = Math.min(nextClearTime, expirationTime);
                            }
                        }
                        newEntries.clear();
                    }
                    if (nextClearTime == Long.MAX_VALUE) {
                        nextClearTime = System.currentTimeMillis() + 1000;
                    }
                    Thread.sleep(Math.max(nextClearTime - System.currentTimeMillis(), 10));
                }
            } catch (Exception e) {
                runCleanUpThread = false;
                e.printStackTrace();
            }
        }).start();
    }

    void put(int key, int value, long durationMs) {
        synchronized (this) {
            ExpirableValue newEntry = new ExpirableValue(value, System.currentTimeMillis() + durationMs);
            map.put(key, newEntry);
            newEntries.put(key, newEntry);
        }
    }

    Integer get(int key) {
        synchronized (this) {
            long currentTime = System.currentTimeMillis();
            ExpirableValue value = map.get(key);
            if (value == null) {
                return null;
            } else if (value.expirationTime < currentTime) {
                System.out.println(DATE_FORMAT.format(new Date()) + ". Clearing " + key + " by get");
                remove(key);
                return null;
            } else {
                return value.value;
            }
        }
    }

    private void remove(int key) {
        synchronized (this) {
            map.remove(key);
            newEntries.remove(key);
        }
    }

    private static class ExpirableValue {
        int value;
        long expirationTime;

        ExpirableValue(int value, long expirationTime) {
            this.value = value;
            this.expirationTime = expirationTime;
        }
    }

    @Override
    public void close() {
        runCleanUpThread = false;
    }
}
