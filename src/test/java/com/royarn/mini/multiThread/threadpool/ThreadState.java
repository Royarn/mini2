package com.royarn.mini.multiThread.threadpool;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
public class ThreadState {

    public static void main(String[] args) {
        new Thread(new TimeWaitting (), "TimeWaitingThread").start();
        new Thread(new Waitting(), "WaitingThread").start();
// 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
    }

    // 该线程在Waiting.class实例上等待
    static class Waitting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waitting.class) {
                    try {
                        Waitting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class TimeWaitting implements Runnable {

        @Override
        public void run() {
            while (true) {
                second(100);
            }
        }
    }

    static class Blocked implements Runnable {

        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    second(100);
                }
            }
        }
    }

    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}
