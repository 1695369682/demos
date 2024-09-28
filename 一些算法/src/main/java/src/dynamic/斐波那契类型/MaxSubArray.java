package src.dynamic.斐波那契类型;

//
public class MaxSubArray {
    public static void main(String[] args) {
        int[] array = new int[]{-20,11,-4,13,-5,-2};
        System.out.println(maxSubArray(array));
    }

        public static int maxSubArray(int[] nums) {
            int len = nums.length;
            // dp[i] 表示：以 nums[i] 结尾的连续子数组的最大和
            int[] dp = new int[len];
            dp[0] = nums[0];
            int res = dp[0];
            for (int i = 1; i < len; i++) {
                if (dp[i - 1] > 0) {
                    dp[i] = dp[i - 1] + nums[i];
                } else {
                    dp[i] = nums[i];
                }
                res = Math.max(res, dp[i]);
            }
            return res;
        }

}
