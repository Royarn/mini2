package com.royarn.mini.thinkinjava.chapter06;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-15
 */
public class PlaceSetting extends Custom {

    DinnerPlate plate;
    Fork fork;
    Spoon spoon;
    Knife knife;

    PlaceSetting(int i) {
        super(i);
        plate = new DinnerPlate(i+1);
        fork = new Fork(i+2);
        spoon = new Spoon(i+3);
        knife = new Knife(i+4);
    }

    public static void main(String[] args) {
        new PlaceSetting(1);
    }
}

class Plate {
    Plate(int i) {
        System.out.println("Plate constructor");
    }
}

class DinnerPlate extends Plate {
    DinnerPlate(int i) {
        super(i);
        System.out.println("DinnerPlate constructor");
    }
}

class Utensil {
    Utensil(int i) {
        System.out.println("Utensil constructor");
    }
}

class Spoon extends Utensil {
    Spoon(int i) {
        super(i);
        System.out.println("Spoon constructor");
    }
}

class Fork extends Utensil {
    Fork(int i) {
        super(i);
        System.out.println("Fork constructor");
    }
}

class Knife extends Utensil {
    Knife(int i) {
        super(i);
        System.out.println("Knife constructor");
    }
}

class Custom {
    Custom(int i) {
        System.out.println("Custom constructor");
    }
}