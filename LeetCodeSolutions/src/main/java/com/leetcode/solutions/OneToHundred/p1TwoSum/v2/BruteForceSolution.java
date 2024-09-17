package com.leetcode.solutions.OneToHundred.p1TwoSum.v2;

public class BruteForceSolution {
    public String twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) return "YES";
            }
        }
        return "NO";
    }

    public static void main(String args[]) {
        BruteForceSolution solObj = new BruteForceSolution();
        int[] nums = {3, 2, 4};
        int target = 6;
        String ans = solObj.twoSum(nums, target);
        System.out.println("This is the answer: " + ans);
    }
}
