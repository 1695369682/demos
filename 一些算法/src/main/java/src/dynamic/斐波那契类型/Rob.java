package src.dynamic.斐波那契类型;

import java.util.Arrays;

//打家劫舍
public class Rob {
    private static int[] nums,tem;
    public static void main(String[] args) {
        int[] nums=new int[]{2,7,9,3,1};
        System.out.println(rob(nums));
    }


    public static int rob(int[] nums) {
        //将已知条件扩大作用域，避免空间溢出
        Rob.nums=nums;
        int len=nums.length-1;
        //用于标记怡运算过的节点，没运算过的都是-1
        tem=new int[nums.length];
        Arrays.fill(tem,-1);

        return dfs(len);
    }
    private static int dfs(int length) {
        //递归结束条件
        if (length<0){
            return 0;
        }
        //计算过的不要再次递归，直接返回
        if(tem[length]!=-1){
            return tem[length];
        }
        int res=Math.max(dfs(length-1),dfs(length-2)+nums[length]);
        //计算过的节点赋值
        tem[length]=res;
        return res;
    }


}
