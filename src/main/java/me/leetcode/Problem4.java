package me.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 */
public class Problem4 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        if (len1 == 0) {
            return getMid(nums2);
        }
        if (len2 == 0) {
            return getMid(nums1);
        }
        int[] nums = new int[len1 + len2];
        int i = 0, j = 0;
        while (i < len1 || j < len2) {
            if (i == len1) {
                nums[i+j] = nums2[j++];
            } else if (j == len2) {
                nums[i+j] = nums1[i++];
            } else {
                if (nums2[j] <= nums1[i]) {
                    nums[i+j] = nums2[j++];
                } else {
                    nums[i+j] = nums1[i++];
                }
            }
        }

        return getMid(nums);
    }

    private double getMid(int[] nums) {
        int len = nums.length;
        if (len % 2 == 0) {
            int mid = len / 2 - 1;
            return (nums[mid] + nums[mid + 1]) / 2.0;
        } else {
            return nums[len / 2];
        }
    }

    public static void main(String[] args) {
        Problem4 problem = new Problem4();
        int[] a = new int[]{1, 3};
        int[] b = new int[]{1, 1, 1, 1, 1, 2, 4};
        double result = problem.findMedianSortedArrays(a, b);
        System.out.println(result);
    }

}
