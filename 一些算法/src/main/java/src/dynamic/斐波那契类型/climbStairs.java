package src.dynamic.斐波那契类型;

import java.util.Arrays;

public class climbStairs {
    private static int[] menu;
    public static void main(String[] args) {
        System.out.println(climbStairs(3));
    }

    public static int climbStairs(int n) {
        climbStairs.menu=new int[n+1];
        Arrays.fill(menu,-1);
        return dfs(n);
    }
    private static int dfs(int n)
    {
        if(n>2)
        {
            menu[1]=1;
            menu[2]=2;
        }
        if(menu[n]!=-1)
        {
            return menu[n];
        }
        if (n==2)
        {
            return 2;
        }
        if (n==1)
        {
            return 1;
        }
        menu[n]=dfs(n-1)+dfs(n-2);
        return menu[n];
    }

}
