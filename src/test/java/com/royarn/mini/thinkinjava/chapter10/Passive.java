package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-28
 */
interface Passive {
    //passive call
    void call(Lanuch lanuch);
}

interface Lanuch {
    void callRemote(Passive passive);
    void passiveCall(String message);
}

class CallT {

    public static Lanuch lanuch() {
        return new Lanuch() {
            @Override
            public void callRemote(Passive passive) {
                passive.call(this);
            }

            @Override
            public void passiveCall(String message) {
                System.out.println("receive message : " + message);
            }
        };
    }

    public static Passive passive() {
        return (lanuch) -> lanuch.passiveCall("this client method will be called ");
    }

    public static void main(String[] args) {
        Lanuch lanuch = CallT.lanuch();
        lanuch.callRemote(CallT.passive());
    }
}