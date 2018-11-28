package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-21
 */
interface ClassIntf {

    void how();

    class IntfImpl implements ClassIntf {
        @Override
        public void how() {
            System.out.println("This a static class implements");
        }
    }
}
class Test {
    public static void main(String[] args) {
        new ClassIntf.IntfImpl().how();
    }
}