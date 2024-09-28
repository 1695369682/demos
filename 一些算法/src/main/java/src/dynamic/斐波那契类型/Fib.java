package src.dynamic.斐波那契类型;

//斐波那契数
//斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
//
//F(0) = 0，F(1) = 1
//F(n) = F(n - 1) + F(n - 2)，其中 n > 1
//给定 n ，请计算 F(n) 。
public class Fib {
    public static void main(String[] args) {
        System.out.println(fib(4));
    }
    public static int fib(int n) {
        int[] arr=new int[n+1];
        arr[0] =0;
        arr[1] =1;
        for (int i=2; i<=n; i++)
        {
            arr[i]=arr[i-1]+arr[i-2];
        }
        return arr[n];
    }
}
