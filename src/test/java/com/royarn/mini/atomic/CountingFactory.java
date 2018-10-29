package com.royarn.mini.atomic;

import com.royarn.mini.annotation.ThreadSafe;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-29
 */
@ThreadSafe
public class CountingFactory {

    private AtomicLong count = new AtomicLong(0);

    public void setCount() {
        count.incrementAndGet();
    }

    public long getCount() {return count.get();}


    public static void main(String[] args) {
        CountingFactory factory = new CountingFactory();
        for (int i=0;i<20;i++) {
            final int temp = i;
           new Thread(
                   () -> {
                       factory.setCount();
                   System.out.println("Thread " + temp + " has finished : " + factory.getCount());
                   }
           ).start();
        }
    }
}