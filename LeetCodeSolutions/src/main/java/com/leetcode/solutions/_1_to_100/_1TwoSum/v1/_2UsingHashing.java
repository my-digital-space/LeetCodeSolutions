package com.leetcode.solutions._1_to_100._1TwoSum.v1;

import java.util.HashMap;

public class _2UsingHashing {
    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        ans[0] = ans[1] = -1;
        HashMap<Integer, Integer> myHashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int currentNumber = nums[i];
            int moreNeeded = target - currentNumber;
            if (myHashMap.containsKey(moreNeeded)) {
                ans[0] = myHashMap.get(moreNeeded);
                ans[1] = i;
                return ans;
            }
            myHashMap.put(nums[i], i);
        }
        return ans;
    }

    public static void main(String args[]) {
        _1BruteForceSolution solObj = new _1BruteForceSolution();
        int[] nums = {3,2,4};
        int target = 6;
        int[] ans = solObj.twoSum(nums, target);
        System.out.println("This is the answer: [" + ans[0] + ", "
                + ans[1] + "]");
    }
}
