package com.royarn.mini.multiThread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
public class ConditionDemo1 {

    private ReentrantLock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();
    private int i = 0;

    /**
     * 生产者
     */
    public void producer() {
        lock.lock();
        try {
            for (;;) {
                i++;
                System.out.println("增加到：" + i);
                if (i >= 30) {
                    System.out.println("等待中......");
                    Thread.sleep(1000);
                    full.await();
                    empty.signal();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    /**
     * 消费者
     */
    public void consumer() {
        lock.lock();
        try {
            for (;;) {
                i--;
                System.out.println("减少到：" + i);
                if (i <= 0) {
                    System.out.println("等待......");
                    Thread.sleep(1000);
                    full.signal();
                    empty.await();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo1 demo1 = new ConditionDemo1();
        new Thread(() ->
            demo1.producer())
                .start();
        Thread.sleep(1000);
        new Thread(() ->
        demo1.consumer())
                .start();
    }
}
