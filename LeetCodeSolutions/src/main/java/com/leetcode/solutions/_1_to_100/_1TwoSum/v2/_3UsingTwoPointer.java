package com.leetcode.solutions._1_to_100._1TwoSum.v2;

import java.util.Arrays;

public class _3UsingTwoPointer {
    public String twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return "YES";
            } else if (sum < target) left++;
            else right--;
        }
        return "NO";
    }

    public static void main(String args[]) {
        _3UsingTwoPointer solObj = new _3UsingTwoPointer();
        int[] nums = {3, 2, 4};
        int target = 6;
        String ans = solObj.twoSum(nums, target);
        System.out.println("This is the answer: " + ans);
    }
}
