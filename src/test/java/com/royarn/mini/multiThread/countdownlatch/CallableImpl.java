package com.royarn.mini.multiThread.countdownlatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
public class CallableImpl implements Callable {

    private CountDownLatch countDownLatch;

    public CallableImpl(CountDownLatch latch) {
        countDownLatch = latch;
    }

    public String doSomething() {
        countDownLatch.countDown();
        return "一分钟就干完了";
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(1000);
        return doSomething();
    }
}
