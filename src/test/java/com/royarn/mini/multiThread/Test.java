package com.royarn.mini.multiThread;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-02-27
 */
public class Test {
    public static void main(String[] args) {
        /*Resourse resourse = new Resourse();
        new Thread(() -> {
            resourse.setId();
        }).start();
        new Thread(() -> {
            resourse.setId2();
        }).start();
        System.out.println(resourse.getId());*/
        Son son = new Son();
        son.setName("Test");
        System.out.println(son);
    }
}

class Resourse {
    private int id = 0;

    public synchronized void setId() {
        try {
            Thread.sleep(1000);
            System.out.println("执行......");
            id++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setId2() {
        try {
            Thread.sleep(5000);
            System.out.println("3秒执行......");
            id++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }
}

class Father {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class Son extends Father {

}