package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-21
 */
public class Parcell2 {

    private class PContents implements Contents {
        @Override
        public String readLabel() {
            return "PContents implements";
        }
    }

    private class RealDestinitions implements Destinitions {
        @Override
        public int value() {
            return 100;
        }
    }

    public Contents contents() { return new PContents(); }
    public Destinitions destinitions() { return new RealDestinitions(); }

    public static void main(String[] args) {
        Contents contents = new Parcell2().contents();
        System.out.println(contents.readLabel());
    }
}



interface Destinitions {
    int value();
}

interface Contents {
    String readLabel();
}