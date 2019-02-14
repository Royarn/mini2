package com.royarn.mini.multiThread.sync;

import com.royarn.mini.annotation.ThreadSafe;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
@ThreadSafe
public class Sync04 implements Runnable {

    static int i = 0;

    public void add() {
        synchronized (Sync03.class) {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        }
    }

    @Override
    public void run() {
        add();
    }

    public static void main(String[] args) throws InterruptedException {
        Sync04 sync04 = new Sync04();
        Sync04 sync05 = new Sync04();
        Thread thread1 = new Thread(sync04);
        Thread thread2 = new Thread(sync05);
        thread1.start();thread2.start();
        thread1.join();thread2.join();
        System.out.println(i);
    }
}
