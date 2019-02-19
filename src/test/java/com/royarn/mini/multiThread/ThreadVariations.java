package com.royarn.mini.multiThread;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-18
 */
public class ThreadVariations {

    public static void main(String[] args) {
        new InnerThread1();
        new InnerThread2("InnerThread-2");
        new InnerRunnable1("InnerRunnable-1");
        new InnerRunable2("InnerRunnable-2");
        new ThreadMethod("ThreadMethod").runTask();
    }
}

class InnerThread1 {
    private int countDown = 5;
    private Helper helper;

    public class Helper extends Thread {
        Helper(String name) {
            super(name);
            start();
        }
        @Override
        public void run() {
            while (true) {
                System.out.println(this);
                if (--countDown == 0) return;
            }
        }

        @Override
        public String toString() {
            return getName() + ": " + countDown;
        }
    }

    public InnerThread1() {
        helper = new Helper("InnerThread-1");
    }
}

class InnerThread2 {
    private int countDown = 5;
    private Thread t;
    public InnerThread2(String name) {
        t = new Thread(name) {
            @Override
            public void run() {
                try {
                    System.out.println(this);
                    if (--countDown == 0) return;
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public String toString() {
                return getName() + ": " + countDown;
            }
        };
        t.start();
    }
}

class InnerRunnable1 {
    private int countDown = 5;
    private Inner inner;
    private class Inner implements Runnable {
        Thread t;
        Inner(String name) {
            t = new Thread(this, name);
            t.start();
        }
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(this);
                    if (--countDown == 0) return;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        @Override
        public String toString() {
            return t.getName() + ": " + countDown;
        }
    }

    public InnerRunnable1(String name) {
        inner = new Inner(name);
    }
}

class InnerRunable2 {
    private int countDown = 5;
    private Thread t;
    public InnerRunable2(String name) {
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(this);
                        if (--countDown == 0) return;
                        Thread.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public String toString() {
                return Thread.currentThread().getName() + ": " +countDown;
            }
        }, name);
        t.start();
    }
}

class ThreadMethod {
    private int countDown = 5;
    private Thread t;
    private String name;
    public ThreadMethod(String name) {
        this.name = name;
    }
    public void runTask() {
        if (t == null) {
            t = new Thread(name) {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println(this);
                            if (--countDown == 0) return;
                            Thread.sleep(10);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public String toString() {
                    return getName() + ": " + countDown;
                }
            };
            t.start();
        }
    }
}