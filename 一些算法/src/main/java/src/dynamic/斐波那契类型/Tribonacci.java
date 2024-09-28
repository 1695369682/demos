package src.dynamic.斐波那契类型;

//和斐波那契一样，这个方法省空间一些
//泰波那契序列 Tn 定义如下：
//
//T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
//
//给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
public class Tribonacci {
    public static void main(String[] args) {
        System.out.println(tribonacci(4));
    }
    public static int tribonacci(int n) {
        if (n<2)
            return n;
        if (n==2)
            return 1;
        int i;
        int p=0;
        int q=1;
        int x=1;
        int res=p+q+x;
        for (i = 3; i < n; i++)
        {
            p=q;
            q=x;
            x=res;
            res=p+q+x;

        }
        return res;
    }
}
