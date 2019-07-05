package com.royarn.mini.datastruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-07-04
 */
public class ArrayList2<T> {

    private T[] element;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList2() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList2(int capacity) {
        element = (T[]) new Object[capacity];
    }

    /**
     * 插入
     */
    public void add(T ele) {
        //是否满容量
        if (size == element.length) {
            ensureCapacity(size * 2);
            element[size++] = ele;
        } else {
            element[size++] = ele;
        }
    }

    /**
     * 修改
     * @param index
     * @param ele
     * @return
     */
    public T set(int index, T ele) {
        T orinalValue = element[index];
        element[index] = ele;
        return orinalValue;
    }

    /**
     * @param index
     * @return
     */
    public T remove(int index) {
        T value = element[index];
        for (int i=index;i<size;i++) {
            element[i] = element[i+1];
        }
        element[--size] = null;
        if (size <= element.length/2) {
            ensureCapacity(size);
        }
        return value;
    }

    public T get(int index) {
        return element[index];
    }

    public int size() {
        return size;
    }

    private void ensureCapacity(int capacity) {
        element = Arrays.copyOf(element, capacity);
    }

    public static void main(String[] args) {
        int nums[] = {9,3,4,5,0,3,2,4,6,3,8};
//        int size = a.length;
//        int target = 3;
//        int i = 0;
//        for (int j = 0 ;j <size;j++) {
//            if (a[j] != target) {
//                a[i] = a[j];
//                i++;
//            }
//        }
//        for (int k = i;k<size;k++) {
//            a[k] = target;
//        }
//        System.out.print("[");
//        for (int x : a) {
//            System.out.print(x + ", ");
//        }
//        System.out.print("]");
//        for (int i=1;i<nums.length;i++) {
//            for (int j = 0; j<nums.length-i;j++) {
//                if (nums[i] < nums[j]) {
//                    int temp = nums[j];
//                    nums[j] = nums[i];
//                    nums[i] = temp;
//                }
//            }
//        }
//        for (int a : nums) {
//            System.out.println(a);
//        }
        String digits = "2324";
        List<String> data = new ArrayList<>();

        for (int i = 0; i< digits.length();i++) {
            data.add(String.valueOf(digits.charAt(i)));
        }
        for (String s : letterCombinations(digits)) {
            System.out.print(s + " ,");
        }
    }

    public static List<String> letterCombinations(String digits) {
        Map<String, String[]> map = new HashMap<>(16);
        map.put("2", new String[]{"a", "b", "c"});
        map.put("3", new String[]{"d", "e", "f"});
        map.put("4", new String[]{"g", "h", "i"});
        map.put("5", new String[]{"j", "k", "l"});
        map.put("6", new String[]{"m", "n", "o"});
        map.put("7", new String[]{"p", "q", "r", "s"});
        map.put("8", new String[]{"t", "u", "v"});
        map.put("9", new String[]{"w", "x", "y", "z"});
        List<String> collections = new ArrayList<>();
        if(digits == null || "".equals(digits)) { return new ArrayList<>(); }

        String first = digits.substring(0,1),
                second = digits.substring(1);
        String[] x = null, y = null;
        if (!"".equals(first) && !"".equals(second)) {
            x = map.get(first);
            y = map.get(second);
            for (int i =0;i<x.length;i++) {
                for (int j=0;j<y.length;j++) {
                    collections.add(x[i] + y[j]);
                }
            }
        } else if (!"".equals(first)) {
            x = map.get(first);
            for (int i =0;i<x.length;i++) {
                collections.add(x[i]);
            }
        } else if (!"".equals(second)) {
            y = map.get(second);
            for (int j=0;j<y.length;j++) {
                collections.add(y[j]);
            }
        }
        return collections;
    }
}