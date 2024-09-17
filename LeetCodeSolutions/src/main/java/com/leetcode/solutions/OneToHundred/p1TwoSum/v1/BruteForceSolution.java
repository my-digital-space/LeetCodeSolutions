package com.leetcode.solutions.OneToHundred.p1TwoSum.v1;

public class BruteForceSolution {
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
        BruteForceSolution solObj = new BruteForceSolution();
        int[] nums = {3,2,4};
        int target = 6;
        int[] ans = solObj.twoSum(nums, target);
        System.out.println("This is the answer: [" + ans[0] + ", "
                + ans[1] + "]");
    }
}
