package com.leetcode.solutions._1_to_100._4_Median_of_Two_Sorted_Arrays.v2;

import java.util.Arrays;

/**
 * Solution 2: Two Pointers Without Extra Space (no extra merged array)
 */
public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // ── Step 1: Get lengths and compute key indices ──────────────────────────
        int m = nums1.length, n = nums2.length;

        // Total number of elements across both arrays combined
        int total = m + n;

        // 'mid' is the index we need to reach in the merged (virtual) sequence.
        // For total=5 (odd)  → mid=2  → the median IS the element at index 2
        // For total=6 (even) → mid=3  → median = avg of elements at index 2 and 3
        // In both cases we walk the loop (mid+1) times: k = 0, 1, ... , mid
        int mid = total / 2;

        // ── Step 2: Two pointers — one per array ─────────────────────────────────

        // i = current read position in nums1
        // j = current read position in nums2
        int i = 0, j = 0;

        // 'curr' holds the element we just picked (the k-th smallest so far)
        // 'prev' holds the element we picked in the PREVIOUS iteration.
        // We track prev because for even-length totals we need BOTH middle elements:
        //   the (mid-1)-th  →  stored in prev  after the (mid)-th iteration
        //   the (mid)-th    →  stored in curr  after the (mid)-th iteration
        int prev = 0, curr = 0;

        // ── Step 3: Simulate a merge, but stop as soon as we hit the median ──────

        // We iterate exactly (mid + 1) times, advancing through the virtual merged
        // array one step at a time without actually building it.
        for (int k = 0; k <= mid; k++) {

            // Slide the window forward: what was 'curr' becomes the new 'prev'
            // so that after the final iteration prev = element just before median,
            // and curr = element AT the median position.
            prev = curr;

            // Standard merge-pick logic: always take the smaller of the two
            // front elements.  Three conditions make us pick from nums1:
            //   (a) nums1 still has elements  (i < m)
            //   (b) AND either nums2 is exhausted (j >= n)
            //       OR the front of nums1 is ≤ the front of nums2
            // Otherwise we pick from nums2.
            if (i < m && (j >= n || nums1[i] <= nums2[j])) {
                curr = nums1[i++];   // pick nums1[i], then advance i
            } else {
                curr = nums2[j++];   // pick nums2[j], then advance j
            }
        }

        // ── Step 4: Extract the median from the two tracked values ───────────────

        // Odd total  → there is one true middle element; it's sitting in 'curr'
        // Even total → the median is the average of the two middle elements,
        //              which are now in 'prev' (index mid-1) and 'curr' (index mid)
        if (total % 2 == 1) return curr;
        else                 return (prev + curr) / 2.0;
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
