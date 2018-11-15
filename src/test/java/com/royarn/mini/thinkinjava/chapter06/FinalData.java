package com.royarn.mini.thinkinjava.chapter06;

import java.util.Random;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-15
 */

/**
 * final 关键字：
 * 作用于域：
 *  在对象初始化过程中， final关键字对不同的类型表现出的结果是不一样的
 *      基本类型在定义时，可在声明时指定初始值，也可放到构造器中,一旦赋值永不可变
 *      操作对象时，可在声明时指定初始值，也可放到构造器中，引用不再可变，但对象内部数据是可能变化的
 * 作用于参数：
 *      基本类型时，值不可更改
 *      引用类型时，不可再更改指向其他任何对象
 *      这一特性常用来把参数传入匿名函数，使其只能读不可写
 * 作用于方法：
 *      禁止继承类覆写
 *  作用于类：
 *      不可被继承
 *
 *  static 关键字
 *      理解为在整个jvm中，属于全局数据，只此一份，final关键字表示为常量数据
 *      配合public表示可被任意类或对象使用
 *
 */
public class FinalData extends Value{

    private static Random random = new Random(47);
    private String id;
    private final int valueOne = 9;
    private static final int VALUE_TWO = 1;
    public FinalData(String id) {
        super(2);
        this.id = id;
    }
}

class Value {
    int i;
    final int f;
    final Value value;
    public Value(int i) {
        f= 1;
        value = new Value(1);
        System.out.println(i);
        this.i = i;
    }

    public static void main(String[] args) {
        new Value(1);
    }
}