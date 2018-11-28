package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-28
 */
interface IntfClass {
    void output();
    class RealClass implements IntfClass {
        @Override
        public void output() {
            System.out.println("This is a real class");
        }

        public static void main(String[] args) {
            new RealClass().output();
        }
    }
}
