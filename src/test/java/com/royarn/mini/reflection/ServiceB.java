package com.royarn.mini.reflection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/6 15:02
 */
public class ServiceB {
    private int a;
    private int b;

    public synchronized int getB() {
        return b;
    }

    public synchronized void setB(int b) {
        try {
            System.out.println(Thread.currentThread().getName() + "线程开始进行赋值......");
            TimeUnit.MILLISECONDS.sleep(5000);
            this.b = b;
            System.out.println(Thread.currentThread().getName() + "线程值执行完成......");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void action() {
        System.out.println("Call for B Service");
    }

    public synchronized int getA() {
        return a;
    }

    public synchronized void setA(int a) {
        try {
            System.out.println(Thread.currentThread().getName() + "线程开始进行赋值......");
            TimeUnit.MILLISECONDS.sleep(5000);
            this.a = a;
            System.out.println(Thread.currentThread().getName() + "线程值执行完成......");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void setA1(int a) {
        try {
            System.out.println(Thread.currentThread().getName() + "线程开始进行赋值......");
            TimeUnit.MILLISECONDS.sleep(5000);
            this.a = a;
            System.out.println(Thread.currentThread().getName() + "线程值执行完成......");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class A implements Runnable {
        private ServiceB b;
        public A(ServiceB b) {
            this.b = b;
        }

        @Override
        public void run() {
            b.setA(30);
        }
    }

    static class B implements Runnable {

        private ServiceB b;
        public B(ServiceB b) {
            this.b = b;
        }
        @Override
        public void run() {
            b.setB(40);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(8);
        list.add(9);
        list.add(7);
        list.add(1);
        list.add(6);
        list.add(2);
        list.add(3);
        list.add(9);
        list.add(4);
        list.add(3);
        int target = 3,size = list.size();
        //[3, 8, 9, 7, 1, 6, 2, 3, 9, 4, 3]  target = 3;
        //1.替换
        for (int i=0;i<size;i++) {
            if (list.get(i).intValue() == target) {
                list.set(i, 22);
            }
        }

        //2.移动
        for (int i=0;i<size;i++) {
            if (list.get(i).intValue() == 22) {
                for (int j=i+1;j<size;j++) {
                    if (list.get(j).intValue() == 22) {
                        list.set(j-1, 22);
                        i = j-1;
                        break;
                    }
                    list.set(j-1, list.get(j));
                }
            }
        }
        System.out.println(list);*/
        /*ServiceB b = new ServiceB();
        System.out.println(b.getA() + b.getB());
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new A(b));
        service.submit(new B(b));
        while (true) {
            //TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("进入方法.....");
            System.out.println(b.getA() + b.getB());
            System.out.println("推出方法.....");
        }*/
        new X(){{
            test1();
            test2();
        }};
    }


    static class X {
        X() {
            System.out.println("X constructor initial .......");
        }
        public void test1() {
            System.out.println("test1");
        }

        public void test2() {
            System.out.println("test2");
        }
    }
}
