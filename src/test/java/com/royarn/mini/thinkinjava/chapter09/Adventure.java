package com.royarn.mini.thinkinjava.chapter09;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-20
 */
public class Adventure {


    public static void main(String[] args) {
        Canfilght canfilght = new Hero();
        canfilght.flight();
        System.out.println(R.a);
    }
}

interface Canfilght {
    void flight();
}

interface Canswim {
    void swim();
}

interface Canfly {
    void fly();
}

class ActionCharacter {
   public void flight() {
       System.out.println("Flight");
   }
}

class Hero extends ActionCharacter implements Canfilght, Canswim, Canfly {

    @Override
    public void swim() {

    }

    public void flight() {
        System.out.println("Hero");
    }

    @Override
    public void fly() {

    }
}

interface I1 {
    void f();
}

interface I2 {
    int f(int i);
}

interface I3 {
    int f();
}

class C {
    public int f() {return 1;}
}

class C2 implements I1, I2 {
    @Override
    public void f() {
    }

    @Override
    public int f(int i) {
        return 1;
    }
}

class C3 extends C implements I2 {
    public int f(int i) {
       return 1;
    }
}

class C4 extends C implements I3 {
    public int f() {return 1;}
}

interface R {
    int a = 10;
}