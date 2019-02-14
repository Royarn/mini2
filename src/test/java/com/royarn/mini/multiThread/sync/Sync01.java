package com.royarn.mini.multiThread.sync;

import com.royarn.mini.multiThread.annotion.ThreadNoSafe;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
@ThreadNoSafe
public class Sync01 implements Runnable {

    static int i = 0;

    @Override
    public void run() {
        add();
    }

    private void add() {
        synchronized (this) {
            for (int j=0;j<10000;j++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Sync01 sync01 = new Sync01();
        Thread thread1 = new Thread(sync01);
        Thread thread2 = new Thread(sync01);
        thread1.start();
        thread2.start();
        thread1.join(); thread2.join();
        System.out.println(i);
    }
}
