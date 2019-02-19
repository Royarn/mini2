package com.royarn.mini.multiThread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
public class ConditionDemo {

    static ReentrantLock lock = new ReentrantLock();
    static Condition full = lock.newCondition();
    static Condition empty = lock.newCondition();
    private static int i =0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() ->
        ConditionDemo.producer()
        ).start();
        Thread.sleep(2000);
        new Thread(() ->
        ConditionDemo.consumer()
        ).start();
    }

    /**
     * 生产者
     */
    public static void producer(){
        lock.lock();
        try {
            for (;;) {
                i++;
                System.out.println("增加到：" + i);
                if (i >= 30) {
                    System.out.println("等待中... ...");
                    Thread.sleep(1000);
                    empty.await();
                    full.signal();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费者
     */
    public static void consumer() {
        lock.lock();
        try {
            for (;;) {
                i--;
                System.out.println("减少到：" + i);
                if (i <= 0) {
                    System.out.println("等待中... ...");
                    Thread.sleep(1000);
                    empty.signal();
                    full.await();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}