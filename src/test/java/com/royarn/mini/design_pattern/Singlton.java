package com.royarn.mini.design_pattern;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-06-20
 */
public class Singlton {

    private static Object o = null;

    public static Object getObj() {
        synchronized (Singlton.class) {
            if (o == null) {
                o = new Object();
            }
            return o;
        }
    }

    private static class ObjHolder {
        public static Resoure resoure = new Resoure();
    }

    public static Resoure getResource() {
        return ObjHolder.resoure;
    }

    public static void main(String[] args) {
    }
}

    class Resoure {
        Resoure() {
            System.out.println("Resource initial ......");
        }
}
