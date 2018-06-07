package com.wizeek.task2

import org.junit.Before
import org.junit.Test

/**
 * Created by Artur on 6/7/2018.
 */
class ExpiringMapTest {

    ExpiringMap map

    @Before
    void setUp() {
        map = new ExpiringMapImpl2()
    }

    @Test
    void test1() {
        map.put(1, 10, 1000)
        assert map.get(1) == 10
        Thread.sleep(1500)
        assert map.get(1) == null
    }

    @Test
    void test2() {
        map.put(1, 10, 1000)
        map.put(1, 25, 2000)
        assert map.get(1) == 25
        Thread.sleep(1500)
        assert map.get(1) == 25
    }

    @Test
    void test3() {
        map.put(1, 10, 2000)
        map.put(1, 25, 1000)
        assert map.get(1) == 25
        Thread.sleep(1500)
        assert map.get(1) == null
    }
}
