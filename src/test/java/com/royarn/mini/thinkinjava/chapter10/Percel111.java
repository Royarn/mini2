package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-21
 */
public class Percel111 {

    protected static class MyDestinition implements Destinitions {
        @Override
        public int value() {
            return 666;
        }
    }

    protected static class MyContents implements Contents {
        @Override
        public String readLabel() {
            return "MyContents implement";
        }
    }

    public static Destinitions destinitions() {
        return new MyDestinition();
    }

    public static Contents contents() {
        return () -> "MyContents lambda implement";
    }

    public static void main(String[] args) {
        System.out.println(Percel111.contents().readLabel());
    }
}
