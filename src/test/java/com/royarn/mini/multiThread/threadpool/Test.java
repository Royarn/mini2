package com.royarn.mini.multiThread.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-06-13
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch latch = new CountDownLatch(70);
        ListHelper<String> helper = new ListHelper<>();
        for (int i=0;i<70;i++) {
            service.submit(() -> {
                helper.putIfAbsent("blink");
                latch.countDown();
            });
        }
        latch.await();
        service.shutdown();
        System.out.println(helper.list);
    }
}

class ListHelper<E>{
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public synchronized boolean putIfAbsent(E ele) {
        boolean absent = list.contains(ele);
        if (!absent) {
            System.out.println("已添加至容器");
            list.add(ele);
        }
        System.out.println("是否存在标识：" + absent);
        return absent;
    }
}