package com.royarn.mini.multiThread.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-14
 */
public class Callable01 implements Callable<Result> {

    private CountDownLatch countDownLatch;

    private Callable01() {}

    public Callable01(CountDownLatch latch) {
        this.countDownLatch = latch;
    }

    @Override
    public Result call() throws Exception {
        System.out.println("executing ... ...");
        Thread.sleep(1000);
        return getInfo();
    }

    public Result getInfo() {
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
        return new Result("200", "success");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //异步化执行
        //Callable01 callable01 = new Callable01();
        //放入一个程序计数器， 异步执行完成后再执行后续步骤
        CountDownLatch latch = new CountDownLatch(1);
        Callable01 callable01 = new Callable01(latch);
        FutureTask<Result> futureTask = new FutureTask<>(callable01);
        new Thread(futureTask).start();
        if (!futureTask.isDone()) {
            latch.await();
            System.out.println("finished");
        }
        System.out.println(futureTask.get());
    }
}


class Result {
    private String code;
    private Object info;

    public Result(String code, Object info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "{ code: " + code + ", info: " + info + " }";
    }
}