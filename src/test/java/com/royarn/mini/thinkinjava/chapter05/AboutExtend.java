package com.royarn.mini.thinkinjava.chapter05;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-09
 */
public class AboutExtend extends Base{

    @Override
    public void scrub() {
        append("AboutExtend scrub()");
        super.scrub();
    }

    public AboutExtend(String name) {
    }

    public void foam() { append("foam()");}

    public static void main(String[] args) {
        AboutExtend extend = new AboutExtend("sdf");
        extend.dilute();
        extend.apply();
        extend.scrub();
        extend.foam();
        extend.println();
    }
}

class Base {

    private String s = new String("Base");
    public void append(String a) {s += a;}
    public void dilute(){ append("dilute()");}
    public void apply() { append("apply()");}
    public void scrub() { append("scrub()");}
    public void println() {
        System.out.println(s);
    }

    public Base() {
        System.out.println("Base has been called");
    }

    public static void main(String[] args) {
        Base base = new Base();
        base.dilute();
        base.apply();
        base.scrub();
        base.println();
    }
}
