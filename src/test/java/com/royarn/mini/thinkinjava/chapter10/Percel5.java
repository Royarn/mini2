package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-21
 */
public class Percel5 {

    class Test {
        private String name;
    }

    public Destinitions destinitions(String str) {
        class MyDestinitions implements Destinitions {
            @Override
            public int value() {
                return 1000;
            }

            private MyDestinitions() {
                System.out.println(str);
            }
        }
        return new MyDestinitions();
    }

    public Destinitions destinitions2(String string) {
        return new Destinitions() {
            private String label = string;
            public String getLabel() {
                return label;
            }
            @Override
            public int value() {
                return 100;
            }
        };
    }

    public static void main(String[] args) {
        Destinitions destinitions = new Percel5().destinitions("blink");
        System.out.println(destinitions.value());
        Destinitions destinitions1 = new Percel5().destinitions2("Flink");
        destinitions1.value();
    }
}
