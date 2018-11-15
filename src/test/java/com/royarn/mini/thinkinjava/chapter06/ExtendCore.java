package com.royarn.mini.thinkinjava.chapter06;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-15
 */
public class ExtendCore extends BaseClass{

    public static void main(String[] args) {
        new ExtendCore();
        BaseClass.tune(new ExtendCore());
    }

    static void tune(ExtendCore core) {
        core.play();
    }
}

class BaseClass {

    void play() {}
    static void tune(BaseClass base) {
        base.play();
    }

    public static void main(String[] args) {
        ExtendCore.tune(new BaseClass());
    }
}
