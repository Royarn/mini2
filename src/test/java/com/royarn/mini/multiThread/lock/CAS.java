package com.royarn.mini.multiThread.lock;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-06-20
 */
public class CAS {

    private int value;

    private synchronized int compareAndSet(int expectedValue, int newValue) {
        if (value == expectedValue) {
            value = newValue;
            return value;
        }
        return value;
    }

    public synchronized boolean compareAndSwap(int expectedValue, int newValue) {
        return expectedValue == compareAndSet(expectedValue, newValue);
    }
}