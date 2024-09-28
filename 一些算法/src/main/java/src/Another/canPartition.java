package src.Another;

import java.util.Arrays;


/**
 * leetcode LCR 101. 分割等和子集
 */
public class canPartition {
    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
    }
    public static boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum%2!=0)
            return false;
        int target=sum/2;

        int[] dp = new int[target+1];
        //初始化
        dp[0] = 0;
        for (int i=0; i< nums.length; i++) {
            for (int j=target; j>=nums[i];j--){
                dp[j]=Math.max(dp[j],dp[j-nums[i]]+nums[i]);
            }
        }

        for (int i=0;i<dp.length;i++){
            System.out.println(dp[i]);
        }
        if (dp[dp.length-1]==target)
        {
            return true;
        }

        return false;
    }
}
