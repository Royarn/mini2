package com.royarn.mini.java8.template;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-19
 */
public class CustomInfo {

    public static void main(String[] args) {
        new OnlineBanking().processCustom("royarn", consumer -> System.out.println("welcome to culture Bank"));
        new OnlineBanking().processCustom("royarn", consumer -> System.out.println("welcome to builder Bank"));
    }
}
