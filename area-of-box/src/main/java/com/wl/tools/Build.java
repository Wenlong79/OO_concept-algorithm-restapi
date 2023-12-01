package com.wl.tools;


public class Build {

    public static ListNode buildNodeAsList(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return null;
        }

        ListNode node = new ListNode(nums[nums.length - 1]);
        for (int i = nums.length - 2; i >= 0; i--) {
            node = new ListNode(nums[i], node);
        }
        return node;
    }

    public static ListNode buildNodeAsReversedList(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return null;
        }
        ListNode node = new ListNode(nums[0]);
        for (int i = 1; i <= nums.length - 1; i++) {
            node = new ListNode(nums[i], node);
        }
        return node;
    }
}
