package com.leetcode.solutions._1_to_100._1TwoSum.v2;

public class _1BruteForceSolution {
    public String twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) return "YES";
            }
        }
        return "NO";
    }

    public static void main(String args[]) {
        _1BruteForceSolution solObj = new _1BruteForceSolution();
        int[] nums = {3, 2, 4};
        int target = 6;
        String ans = solObj.twoSum(nums, target);
        System.out.println("This is the answer: " + ans);
    }
}
