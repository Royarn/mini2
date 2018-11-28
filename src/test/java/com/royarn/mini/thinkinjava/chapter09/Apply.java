package com.royarn.mini.thinkinjava.chapter09;

import java.util.Arrays;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-20
 */
public class Apply {

    private static final String str = "BLINK";

    public static void process(Processor processor, Object o) {
        System.out.println(processor.process(o));
    }

    public static void main(String[] args) {
        process(new Upcase(), str);
        process(new Downcase(), str);
        process(new Splliter(), str);
    }
}

class Processor {
    Object process(Object input) {
        return input;
    }
}

class Upcase extends Processor {
    @Override
    String process(Object input) {
        return ((String) input).toUpperCase();
    }
}

class Downcase extends Processor {
    @Override
    String process(Object input) {
        return ((String) input).toLowerCase();
    }
}

class Splliter extends Processor {
    @Override
    String process(Object input) {
        return Arrays.toString(((String) input).split(" "));
    }
}
