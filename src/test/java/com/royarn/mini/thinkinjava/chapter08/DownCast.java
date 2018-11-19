package com.royarn.mini.thinkinjava.chapter08;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-16
 */
public class DownCast {

    public static void main(String[] args) {
        Userful[] userfuls = {
                new Userful(),
                new MoreUseful()
        };
        ((MoreUseful)userfuls[0]).l();
        ((MoreUseful)userfuls[1]).l();
    }
}

class Userful {
    void f() {}
    void g() {}
}

class MoreUseful extends Userful {

    void f() {}
    void g() {}
    void h() {}
    void l() {}
}