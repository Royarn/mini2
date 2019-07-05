package com.royarn.mini.multiThread.components;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-06-14
 */
public class CountDownLatchTest2 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(200);
        Resource resource = new Resource(latch);
        for (int i=0;i<200;i++) {
            service.execute(() -> resource.setValue());
        }
        latch.await();
        System.out.println("获取计算值：" + resource.getValue());
        service.shutdown();
    }
}

class Resource {
    private AtomicInteger value = new AtomicInteger(0);
    private CountDownLatch latch;
    public Resource(CountDownLatch latch) {
        this.latch = latch;
    }

    public int getValue() {
        return this.value.get();
    }

    public void setValue() {
        this.value.incrementAndGet();
        latch.countDown();
    }
}
