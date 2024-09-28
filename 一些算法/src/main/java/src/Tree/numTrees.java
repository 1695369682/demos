package src.Tree;

public class numTrees {
    public static void main(String[] args) {
        System.out.println(numTrees(4));
    }

    public static int numTrees(int n) {
                int[] dp=new int[n+1];
                if(n<2)
                    return n;
                dp[0] =1;
                dp[1] =1;
                dp[2] =2;
                for (int i=3; i<=n;i++)
                {
                    for(int j=1;j<=i;j++)
                    {
                        dp[i]=dp[j-1]*dp[i-j]+dp[i];
                    }
                }
                return dp[n];
    }
}
