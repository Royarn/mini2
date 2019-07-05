package com.royarn.mini.multiThread.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-06-19
 */
public class CountDownLatch {

    private final Sync sync;

    private class Sync extends AbstractQueuedSynchronizer {
        int getCount() {
            return getState();
        }

       public void signal() {
           sync.releaseShared(0);
       }
    }

    public CountDownLatch(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("");
        }
        sync = new Sync();
    }
}