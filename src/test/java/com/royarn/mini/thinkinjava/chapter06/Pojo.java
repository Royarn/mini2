package com.royarn.mini.thinkinjava.chapter06;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-15
 */
public class Pojo extends Flnk{

    public void setAge(String age) {
        this.age = age;
    }

    public Pojo(String name) {
        System.out.println(name);
    }

    public static void main(String[] args) {
        System.out.println(new Pojo("HHA"));
        Flnk.main(args);
    }
}

class Flnk {

     String age;

    public Flnk() {
        System.out.println("super class will be called ... ");
    }

    public Flnk(String name) {
        System.out.println(name);
    }

    public static void main(String[] args) {
        System.out.println("Flnk main()");
    }
}
