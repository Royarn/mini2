package com.royarn.mini.multiThread.sync;

import com.royarn.mini.multiThread.annotion.ThreadNoSafe;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
@ThreadNoSafe
public class Sync03 implements Runnable {

    static int i =0;

    public synchronized void add() {
        for (int j = 0; j < 10000; j++) {
            i++;
        }
    }

    @Override
    public void run() {
        add();
    }

    public static void main(String[] args) throws InterruptedException {
        Sync03 sync03 = new Sync03();
        Sync03 sync04 = new Sync03();
        Thread thread1 = new Thread(sync03);
        Thread thread2 = new Thread(sync03);
        thread1.start();
        thread2.start();
        thread1.join(); thread2.join();
        System.out.println(i);
    }
}
