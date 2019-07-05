package com.royarn.mini.multiThread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-06-19
 */
public class ConcurrentCache<E> {
    /**
     * 构建并发版本的缓冲
     */
    private static final int CAPACITY = 10;
    private E[] items;
    private ReentrantLock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition empty = lock.newCondition();
    private int size;

    public ConcurrentCache() {
        this(CAPACITY);
    }

    public ConcurrentCache(int capacity) {
        items = (E[]) new Object[capacity];
    }

    public void put(E e) {
        lock.lock();
        try {
            while (size == items.length)
                empty.await();
            System.out.println("生产数据时的数据量：" + size);
            items[size++] = e;
            if (size == items.length) {
                System.out.println("生产者到达边界：" + size + " ----- " + items.length);
                full.signal();
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public E take() {
        lock.lock();
        try {
            while (size == 0)
                full.await();
            System.out.println("消费数据时的数据量：" + size);
            E e = items[--size];
            items[size] = null;
            if (size == 0) {
                empty.signal();
            }
            return e;
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public static void main(String[] args) {
        /*ConcurrentCache<String> cache = new ConcurrentCache<>();
        new Thread(() -> {
            try {
                while (true) {
                    TimeUnit.MILLISECONDS.sleep(5000);
                    cache.put(UUID.randomUUID().toString());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        new Thread(() -> {
            try {
                while (true) {
                    System.out.println("缓存中数据：" + cache.take());
                    TimeUnit.MILLISECONDS.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/

        int[] a = {1, 5, 23, 34};
        int index = 2;
        System.out.println(a[--index]);
        System.out.println(index);
    }
}
