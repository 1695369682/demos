package src.dynamic.斐波那契类型;

public class MinCostClimbingStairs {
    public static void main(String[] args) {
        int[] arr=new int[]{1,100,1,1,1,100,1,1,100,1};
        System.out.println(minCostClimbingStairs(arr));
    }
    public static int minCostClimbingStairs(int[] cost) {
        //tem[i]：到第i个位置的花费
        int[] tem=new int[cost.length+1];
        //初始化数组，到达0，1花费都是0；
        tem[0]=tem[1]=0;

        for (int i=2;i<=cost.length;i++)
        {
            tem[i]=Math.min(tem[i-1]+cost[i-1],tem[i-2]+cost[i-2]);
            System.out.println(tem[i]);
        }
//        Arrays.sort(tem);
//        for (int i:tem){
//            System.out.println(i);
//        }
        return tem[cost.length];
    }
}
