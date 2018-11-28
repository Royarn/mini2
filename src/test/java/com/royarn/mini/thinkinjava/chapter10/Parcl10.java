package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-28
 */
public class Parcl10 {

    interface Destination {
        String readLabel();
    }

    public Destination destination(String input) {
        return () -> input + "final";
    }

    public static void main(String[] args) {
        //System.out.println(new Parcl10().destination("Inp_").readLabel());
        Destination destination = new Parcl10().destination1("Yahoo", 10000);
        System.out.println(destination.readLabel());
    }

    /**
     *
     * @param dest
     * @param price
     * @return
     * maybe JDK 1.8 before , if use method param , you must be declared by final , but now if you only read , you don'y have to
     */
    public Destination destination1(String dest, float price) {
        return new Destination() {
            private int cost;
            private String label = dest;
            {
                cost = Math.round(price);
                if (cost > 100) {
                    System.out.println("Over budget");
                }
            }
            @Override
            public String readLabel() {
                return label;
            }
        };
    }
}
