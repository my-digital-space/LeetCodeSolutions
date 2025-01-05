package com.leetcode.solutions._1_to_100._1TwoSum.v2;

import java.util.HashMap;

public class _2UsingHashing {
    public String twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> myHashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int currentNumber = nums[i];
            int moreNeeded = target - currentNumber;
            if (myHashMap.containsKey(moreNeeded)) {
                return "YES";
            }
            myHashMap.put(nums[i], i);
        }
        return "NO";
    }

    public static void main(String args[]) {
        _2UsingHashing solObj = new _2UsingHashing();
        int[] nums = {3, 2, 4};
        int target = 6;
        String ans = solObj.twoSum(nums, target);
        System.out.println("This is the answer: " + ans);
    }
}
