package com.leetcode.solutions._1_to_100._4_Median_of_Two_Sorted_Arrays.v1;

import java.util.Arrays;

/**
 * Solution 1 (Brute Force Method) (Merge + Find Median)
 */
public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] merged = new int[m + n]; // Create a new array to store merged result
        int i = 0, j = 0, k = 0; // i -> pointer for nums1, j -> pointer for nums2, k -> pointer for merged array

        // Merge both arrays while both have elements
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }

        // If nums1 still has remaining elements, copy them
        // (this happens when nums2 is exhausted first)
        while (i < m) {
            merged[k++] = nums1[i++];
        }

        // If nums2 still has remaining elements, copy them
        // (this happens when nums1 is exhausted first)
        while (j < n) {
            merged[k++] = nums2[j++];
        }

        int total = m + n;
        if (total % 2 == 1) {
            // If total length is odd, Median is the middle element
            return merged[total / 2];
        } else {
            // If total length is even
            // Median is the average of two middle elements
            int leftMid = merged[total / 2 - 1];
            int rightMid = merged[total / 2];
            return (leftMid + rightMid) / 2.0; // divide by 2.0 to get double
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // 1️⃣ Basic odd length
        test(sol, new int[]{1, 3}, new int[]{2}, 2.0);

        // 2️⃣ Basic even length
        test(sol, new int[]{1, 2}, new int[]{3, 4}, 2.5);

        // 3️⃣ One array empty
        test(sol, new int[]{}, new int[]{1}, 1.0);
        test(sol, new int[]{}, new int[]{1, 2, 3, 4}, 2.5);

        // 4️⃣ Both arrays size 1
        test(sol, new int[]{1}, new int[]{2}, 1.5);

        // 5️⃣ Different sizes
        test(sol, new int[]{1, 3, 8}, new int[]{7, 9, 10, 11}, 8.0);

        // 6️⃣ All elements of one smaller
        test(sol, new int[]{1, 2, 3}, new int[]{10, 11, 12}, 6.5);

        // 7️⃣ Duplicates
        test(sol, new int[]{1, 2, 2}, new int[]{2, 2, 3}, 2.0);

        // 8️⃣ Negative numbers
        test(sol, new int[]{-5, -3, -1}, new int[]{-2}, -2.5);

        // 9️⃣ Mixed positive & negative
        test(sol, new int[]{-3, -1, 4}, new int[]{-2, 2, 3}, 0.5);

        // 🔟 Large difference in sizes
        test(sol, new int[]{1}, new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10}, 5.5);

        // 1️⃣1️⃣ Zeros
        test(sol, new int[]{0, 0}, new int[]{0, 0}, 0.0);

        // 1️⃣2️⃣ Single large array
        test(sol, new int[]{1, 2, 3, 4, 5}, new int[]{}, 3.0);

        // 1️⃣3️⃣ Edge extreme values
        test(sol, new int[]{Integer.MIN_VALUE}, new int[]{Integer.MAX_VALUE}, -0.5);

        // 1️⃣4️⃣ Already interleaved
        test(sol, new int[]{1, 4, 7}, new int[]{2, 3, 5, 6}, 4.0);

        // 1️⃣5️⃣ Same arrays
        test(sol, new int[]{1, 2, 3}, new int[]{1, 2, 3}, 2.0);
    }

    private static void test(Solution sol, int[] nums1, int[] nums2, double expected) {
        double result = sol.findMedianSortedArrays(nums1, nums2);

        System.out.println("nums1 = " + Arrays.toString(nums1));
        System.out.println("nums2 = " + Arrays.toString(nums2));
        System.out.println("Expected = " + expected + ", Actual = " + result);

        if (Math.abs(result - expected) < 1e-6) {
            System.out.println("✅ PASS");
        } else {
            System.out.println("❌ FAIL");
        }

        System.out.println("-----------------------------------");
    }
}
