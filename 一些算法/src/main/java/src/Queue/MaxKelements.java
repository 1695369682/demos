package src.Queue;

import java.util.PriorityQueue;

public class MaxKelements {

    public static void main(String[] args) {
        int[] nums=new int[]{10,10,10,10,10};//模拟数组
        int k=3;//模拟执行次数

        int i=0;//循环辅助变量
        long res=0;//结果
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> b - a);
        //通过lamada表达式修改出队顺序
        while (i++<k) {
            for (int num : nums) {
                queue.offer(num);
            }
            int max=queue.poll();
            res+=max;
            if(max%3!=0)
                queue.offer(max/3+1);
            else
                queue.offer(max/3);
        }
        System.out.println(res);
    }


}
