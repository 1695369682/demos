package src.Array;

import java.util.Arrays;

public class Solution2 {
    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5,6,7};
        rotate(a,3);
    }

    private static void rotate(int[] a, int i) {
        for (int j = 0; j < i; j++) {
            int temp =a[j];
            a[j] = a[a.length -j-1];
            a[a.length -j-1] = temp;

        }
        System.out.println(Arrays.toString(a));
    }
}
