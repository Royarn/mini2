package com.royarn.mini.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-07-04
 */
public class Easy {

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int minus = target - nums[i];
            for (int j = i +1;j<nums.length;j++) {
                if (minus == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i =0;i<nums.length;i++) {
            int minus = target - nums[i];
            if (map.containsKey(minus)) {
                return new int[]{map.get(minus), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static char[] reverString(char[] s) {
        for (int i=0,j=s.length-1; i<s.length-i&&j<s.length-i;i++,j--) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
        }
        return s;
    }
}
