package com.royarn.mini.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-06-03
 */
public class TestGeneric<T> {

    private List<T> datas = new ArrayList<>();

    public void add(T data) {
        datas.add(data);
    }

    public T getSome(int index) {
        return datas.get(index);
    }

    public static void main(String[] args) {
        TestGeneric<String> test = new TestGeneric<>();
        test.add("blink");
        System.out.println(test.getSome(0));
        TestGeneric<String>[] ts = new TestGeneric[2];
        TestGeneric<String>[] generics = new TestGeneric[10];
        generics[0] = new TestGeneric<>();
        generics[1] = new TestGeneric<>();
        System.out.println(generics);
    }
}

interface HasColor {
    String getColor();
}

class HH implements HasColor {
    @Override
    public String getColor() {
        return null;
    }
}

class Test<T> {
}

class RealClass<T extends HasColor> extends Test<T> {

}