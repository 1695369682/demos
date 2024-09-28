package src.notAC;

import java.util.Arrays;

public class minSubArrayLen {
    public static void main(String[] args) {
        System.out.println(minSubArrayLen(213, new int[]{12,28,83,4,25,26,25,2,25,25,25,12}));
    }

        public static int minSubArrayLen(int target, int[] nums) {
            Arrays.sort(nums);
            int res=0;
            int flag=0;
            for(int i=nums.length-1;i>=0;i--)
            {
                System.out.println(res+"::"+flag);
                if(flag>=target)
                {
                    return res;
                }
                else
                {
                    flag+=nums[i];
                    res++;
                }
            }

            if(flag<target)
                return 0;
            return res;
        }

}
