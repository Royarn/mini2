package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-28
 */
public class Parcel9 {

    class Wrapping {
        private int num;
        public Wrapping(int num) {
            this.num = num;
        }
        public int getNum() { return num; }
    }

    public Wrapping wrapping(int num) {
        return new Wrapping(num) {
            @Override
            public int getNum() {
                return super.getNum() * 2;
            }
        };
    }

    public static void main(String[] args) {
        System.out.println(new Parcel9().wrapping(10).getNum());
    }
}
