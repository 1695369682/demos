package src.dynamic.斐波那契类型;

import java.util.Arrays;

//给你一个整数数组 nums ，你可以对它进行一些操作。
//
//每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。
// 之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
//
//开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
public class DeleteAndEarn {
    public static void main(String[] args) {
        int[] nums = new int[]{2,2,3,3,3,4};
        System.out.println(deleteAndEarn(nums));
    }
    public static int deleteAndEarn(int[] nums) {
        int n=nums.length;
        Arrays.sort(nums);
        int[] tem=new int[nums[n-1]+1];
        int i=0;
        for (i=0;i<nums.length;i++)
        {
                tem[nums[i]]++;
        }

        int[] dp=new int[tem.length];
        dp[1]=tem[1]*1;
        dp[0]=0;
        for (i=2;i< tem.length;i++)
        {
            dp[i]=Math.max(tem[i]*i+dp[i-2],dp[i-1]);
        }
//        for (int x:dp)
//        {
//            System.out.println(x);
//        }
        return dp[tem.length-1];
    }
}
