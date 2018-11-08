package com.royarn.mini.thinkinjava.chapter03;

import java.util.*;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-08
 */
//: BitManipulation.java
// Using the bitwise operators
/**
 * boolean 1 位 - - Boolean
 * char 16 位 Unicode 0 Unicode 2 的 16 次方-1 Character
 * byte 8 位 -128 +127 Byte（注释①）
 * short 16 位 -2 的15 次方 +2 的 15 次方-1 Short（注释①）
 * int 32 位 -2 的 31 次方 +2 的31 次方-1 Integer
 * long 64 位 -2 的63 次方 +2 的 63 次方-1 Long
 * float 32 位 IEEE754 IEEE754 Float
 * double 64 位 IEEE754 IEEE754 Double
 */
public class BitManipulation {

    public static void main(String[] args) {

        Random rand = new Random();
        int i = rand.nextInt();
        int j = rand.nextInt();
        pBinInt("-1", -1);
        pBinInt("+1", +1);
        int maxpos = 2147483647;
        pBinInt("maxpos", maxpos);
        int maxneg = -2147483648;
        pBinInt("maxneg", maxneg);
        pBinInt("i", i);
        pBinInt("~i", ~i);
        pBinInt("-i", -i);
        pBinInt("j", j);
        pBinInt("i & j", i & j);
        pBinInt("i | j", i | j);
        pBinInt("i ^ j", i ^ j);
        pBinInt("i << 5", i << 5);
        pBinInt("i >> 5", i >> 5);
        pBinInt("(~i) >> 5", (~i) >> 5);
        pBinInt("i >>> 5", i >>> 5);
        pBinInt("(~i) >>> 5", (~i) >>> 5);
        long l = rand.nextLong();
        long m = rand.nextLong();
        pBinLong("-1L", -1L);
        pBinLong("+1L", +1L);
        long lln = -9223372036854775808L;
        pBinLong("maxneg", lln);
        pBinLong("l", l);
        pBinLong("~l", ~l);
        pBinLong("-l", -l);
        pBinLong("m", m);
        pBinLong("l & m", l & m);
        pBinLong("l | m", l | m);
        pBinLong("l ^ m", l ^ m);
        pBinLong("l << 5", l << 5);
        pBinLong("l >> 5", l >> 5);
        pBinLong("(~l) >> 5", (~l) >> 5);
        pBinLong("l >>> 5", l >>> 5);
        pBinLong("(~l) >>> 5", (~l) >>> 5);
    }
    static void pBinInt(String s, int i) {
        System.out.println(
                s + ", int: " + i + ", binary: ");
        System.out.print(" ");
        for(int j = 31; j >=0; j--)
            if(((1 << j) & i) != 0)
                System.out.print("1");
            else
                System.out.print("0");
        System.out.println();
    }
    static void pBinLong(String s, long l) {
        System.out.println(
                s + ", long: " + l + ", binary: ");
        System.out.print(" ");
        for(int i = 63; i >=0; i--)
            if(((1L << i) & l) != 0)
                System.out.print("1");
            else
                System.out.print("0");
        System.out.println();
    }
}