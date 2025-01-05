package com.leetcode.solutions._1_to_100._1TwoSum.v1;

public class _1BruteForceSolution {
    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        ans[0] = ans[1] = -1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    ans[0] = i;
                    ans[1] = j;
                    return ans;
                }
            }
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
