package src.hash;

import java.util.HashMap;
import java.util.Map;

public class twoSum {
    public static void main(String[] args) {
        System.out.println(twoSum(new int[]{3,3}, 6)[0]);
        System.out.println(twoSum(new int[]{3,3}, 6)[1]);
    }
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] result=new int[2];

        for (int i=0; i<nums.length; i++)
        {
            if (map.containsKey(target-nums[i]))
            {
                result[0]=i;
                result[1]=map.get(target-nums[i]);

                return result;
            }
            map.put(nums[i],i);
        }
        return result;
    }
}

