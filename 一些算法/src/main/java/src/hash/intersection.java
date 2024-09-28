package src.hash;

import java.util.HashSet;
import java.util.Set;

public class intersection {
    public static void main(String[] args) {
        System.out.println(intersection(new int[]{1, 2, 3}, new int[]{1, 2, 3})[0]);
    }
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> res = new HashSet<Integer>();

            for (int i = 0; i < nums1.length; i++)
            {
                set.add(nums1[i]);
            }
            for (int i = 0; i < nums2.length; i++)
            {
                if (set.contains(nums2[i]))
                    res.add(nums2[i]);
            }
        int[] intArray = res.stream().mapToInt(Integer::intValue).toArray();
            return intArray;

    }
}
