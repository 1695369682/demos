package src.Another;

import java.util.Arrays;

//最后一块石头的重量2--和canPartition差不多思路
public class lastStoneWeightⅡ {
    public static void main(String[] args) {
        System.out.println(lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
    }
    public static int lastStoneWeight(int[] stones) {
        int sum = Arrays.stream(stones).sum();

        int target=sum/2;

        int[] dp = new int[target+1];
        //初始化
        dp[0] = 0;
        for (int i=0; i< stones.length; i++) {
            for (int j=target; j>=stones[i];j--){
                dp[j]=Math.max(dp[j],dp[j-stones[i]]+stones[i]);
            }
        }


        for (int i=0;i<dp.length;i++){
            System.out.println(dp[i]);
        }
        int result=sum-dp[dp.length-1]-dp[dp.length-1];
        if (result<0)
            return -result;
        return result;
    }
}
