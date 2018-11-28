package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-21
 */
public class Parcell {

    class Contents {
        private int i = 11;
        public int value() { return i; }
    }
    class Destionation {
        private String label;
        Destionation(String whereTo) {
            label = whereTo;
        }
        String readLabel() {
            return label;
        }
    }

    public Contents getContents() {
        return new Contents();
    }

    public void ship(String dest) {
        Contents contents = getContents();
        Destionation destionation = new Destionation(dest);
        System.out.println(destionation.readLabel());
        Selector selector = new Sequence(10).selector();
        while (selector.hasNext()) {
            System.out.println(selector.next());
        }
    }

    public static void main(String[] args) {
        Parcell parcell = new Parcell();
        parcell.ship("Tasmania");
    }
}