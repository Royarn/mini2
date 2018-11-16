package com.royarn.mini.thinkinjava.chapter08;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-16
 */

/**
 * since jdk 1.5
 *  you can use more detail type for inport class when you overwritten
 */
public class CovariantReturn {

    public static void main(String[] args) {
        Mill mill = new Mill();
        Grain grain = mill.process();
        System.out.println(grain);
        mill = new WheatMill();
        grain = mill.process();
        System.out.println(grain);
    }
}

class Grain {
    @Override
    public String toString() {
        return "Grain";
    }
}

class Wheat extends Grain {
    @Override
    public String toString() {
        return "Wheat";
    }
}

class Mill {
    Grain process() {
        return new Grain();
    }
}

class WheatMill extends Mill {

    @Override
    Wheat process() {
        return new Wheat();
    }
}
