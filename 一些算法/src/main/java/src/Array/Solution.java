package src.Array;

import java.util.Arrays;

/**
 * 189. 轮转数组
 */
class Solution {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotate2(a, 3);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    //超时
    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        if (k > len)
            k = k % len;
        int n = len - 1;//从后往前遍历
        int m = 0;//记录最后一个元素，防止被覆盖，数据丢失

        for (int i = 0; i < k; i++) {
            m = nums[n];
            while (n > 0) {
                nums[n] = nums[n - 1];
                n--;
            }
            n = len - 1;
            nums[0] = m;
        }

    }

    public static void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int[] temp2 = new int[n];
        for (int i = 0; i < n; i++) {
            int target=(i + k) % n;
            System.out.println(target);
            temp2[target]=nums[i];
        }
        System.arraycopy(temp2, 0, nums, 0, n);
    }
}
