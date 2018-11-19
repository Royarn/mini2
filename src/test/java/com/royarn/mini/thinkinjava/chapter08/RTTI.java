package com.royarn.mini.thinkinjava.chapter08;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-19
 */
public class RTTI {

    public static void main(String[] args) {
        Cycle[] cycles = {
          new Unicycle(),
          new Bicycle(),
          new Tricycle()
        };
        ((Unicycle)cycles[0]).balance();
        ((Bicycle)cycles[1]).balance();
        ((Bicycle)cycles[2]).balance();
    }
}

class Cycle {
    void f() {}
    void g() {}
}

class Unicycle extends Cycle {
    void balance() {}
}

class Bicycle extends Cycle {
    void balance() {}
}

class Tricycle extends Cycle {
}