package com.leetcode.solutions._1_to_100._4_Median_of_Two_Sorted_Arrays.v3;

import java.util.Arrays;

/**
 * Solution 3: Binary Search (Optimal) (binary search on partition)
 */
public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // ── Step 1: Always binary search on the SMALLER array ───────────────────
        // This guarantees O(log(min(m,n))) time.
        // If nums1 is larger, swap them so nums1 is always the shorter one.
        // Correctness is unaffected — median doesn't care which array is "first".
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;   // length of the SMALLER array (we binary search this)
        int n = nums2.length;   // length of the LARGER  array

        // ── Step 2: Set up the binary search window on nums1 ────────────────────
        // p1 can range from 0 (take nothing from nums1 into the left half)
        //                 to m (take everything from nums1 into the left half)
        int lo = 0, hi = m;

        while (lo <= hi) {

            // ── Step 3: Pick a partition point for each array ───────────────────

            // p1 = how many elements of nums1 go into the LEFT half
            // We try the midpoint of the current search window.
            int p1 = (lo + hi) / 2;

            // p2 = how many elements of nums2 go into the LEFT half
            // The left half must contain exactly half the total elements
            // (we use (m+n+1)/2 — the +1 handles odd totals by giving the extra
            //  element to the left, so the median is always max(left half)).
            int p2 = (m + n + 1) / 2 - p1;

            // ── Step 4: Read the four boundary values ───────────────────────────
            //
            //   nums1: [ ... | nums1[p1-1] ]  [ nums1[p1] | ... ]
            //                  maxLeft1           minRight1
            //
            //   nums2: [ ... | nums2[p2-1] ]  [ nums2[p2] | ... ]
            //                  maxLeft2           minRight2
            //
            // Use ±Infinity as sentinels when the partition is at an edge:
            //   p1 == 0  → nothing in nums1's left  → treat as -∞ (always valid left)
            //   p1 == m  → nothing in nums1's right → treat as +∞ (always valid right)
            //   same logic for p2 == 0 and p2 == n

            int maxLeft1  = (p1 == 0) ? Integer.MIN_VALUE : nums1[p1 - 1];
            int minRight1 = (p1 == m) ? Integer.MAX_VALUE : nums1[p1];

            int maxLeft2  = (p2 == 0) ? Integer.MIN_VALUE : nums2[p2 - 1];
            int minRight2 = (p2 == n) ? Integer.MAX_VALUE : nums2[p2];

            // ── Step 5: Check if we found the correct partition ──────────────────
            //
            // A valid partition satisfies TWO cross-conditions:
            //   (A) maxLeft1 <= minRight2  →  left side of nums1 ≤ right side of nums2
            //   (B) maxLeft2 <= minRight1  →  left side of nums2 ≤ right side of nums1
            //
            // Together they ensure: every element on the LEFT ≤ every element on the RIGHT
            // across BOTH arrays combined.

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {

                // ✅ Valid partition — extract the median

                // ODD total: the median is the largest value in the left half
                //   (the left half has one more element than the right half)
                if ((m + n) % 2 == 1) {
                    return Math.max(maxLeft1, maxLeft2);
                }

                // EVEN total: median = average of the largest-left and smallest-right
                //   max of left half  →  Math.max(maxLeft1, maxLeft2)
                //   min of right half →  Math.min(minRight1, minRight2)
                else {
                    return (Math.max(maxLeft1, maxLeft2) +
                            Math.min(minRight1, minRight2)) / 2.0;
                }
            }

            // ── Step 6: Adjust the binary search window ──────────────────────────

            // maxLeft1 > minRight2 means nums1's left boundary is TOO BIG —
            // we took too many elements from nums1 into the left half.
            // Move p1 LEFT by shrinking the upper bound.
            else if (maxLeft1 > minRight2) {
                hi = p1 - 1;
            }

            // maxLeft2 > minRight1 means nums2's left boundary is TOO BIG —
            // we took too FEW elements from nums1 into the left half.
            // Move p1 RIGHT by raising the lower bound.
            else {
                lo = p1 + 1;
            }
        }

        return 0.0; // unreachable: problem guarantees at least one element total
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
