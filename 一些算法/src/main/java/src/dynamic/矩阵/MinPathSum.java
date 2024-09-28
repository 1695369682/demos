package src.dynamic.矩阵;

public class MinPathSum {
    public static void main(String[] args) {
       int[][] grid={{1,2,3},{4,5,6}};
               //grid={{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minPathSum(grid));
    }
    public static int minPathSum(int[][] grid) {
        int x=grid.length;
        int y=grid[0].length;

        int[][] dp = new int[x+1][y];
        dp[0][0]=grid[0][0];
        for (int i=1; i<x; i++)
            dp[i][0]=grid[i][0]+dp[i-1][0];
        for (int i=1; i<y; i++)
            dp[0][i]=grid[0][i]+dp[0][i-1];
        for(int i=0;i<=x-1;i++)
        {
            System.out.println();
            for (int j=0;j<=y-1;j++)
                System.out.print(dp[i][j]);}

        for (int i=1; i<x; i++)
            for (int j=1; j<y; j++)
                dp[i][j]=Math.min(grid[i][j]+dp[i][j-1], grid[i][j]+dp[i-1][j]);
        for(int i=0;i<=x-1;i++)
        {
            System.out.println();
            for (int j=0;j<=y-1;j++)
                System.out.print(dp[i][j]);}

        /**打印dp数组
        for(int i=0;i<=x-1;i++)
        {
            System.out.println();
            for (int j=0;j<=y-1;j++)
                System.out.print(dp[i][j]);
        }*/
        return dp[x-1][y-1];
    }
}
