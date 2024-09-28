package src.dynamic.矩阵;

//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
//
//机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
//矩阵类似如下
//0111111
//1000000
//1000000
//问总共有多少条不同的路径？

//升级版：数组中有个障碍物obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
//有多少路径
public class UniquePaths {
    public static void main(String[] args) {
        int[][] paths = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        System.out.println("最短路径："+uniquePaths(3, 7));
        System.out.println("最短路径2："+uniquePathsWithObstacles(paths));
    }
    public static int uniquePaths(int m, int n) {
        int[][] dp=new int[m+1][n+1];
        //1*1的格子有一种
        dp[0][0]=1;
        for(int i=1;i<=n-1;i++)
            dp[0][i]=1;
        for(int i=1;i<=m-1;i++)
            dp[i][0]=1;


        for(int i=1;i<=m-1;i++)
            for (int j=1;j<=n-1;j++)
                dp[i][j]=dp[i-1][j]+dp[i][j-1];

        /**打印dp数组
          for(int i=0;i<=m-1;i++)
         {
           System.out.println();
           for (int j=0;j<=n-1;j++)
               System.out.print(dp[i][j]);
         }*/
        return dp[m-1][n-1];
    }
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        int[][] dp=new int[m+1][n+1];
        if(obstacleGrid[0][0]==1||obstacleGrid[m-1][n-1]==1)
        {
            return 0;
        }
        //1*1的格子有一种
        dp[0][0]=1;
        for(int i=1;i<=n-1&&obstacleGrid[0][i]==0;i++)
            dp[0][i]=1;
        for(int i=1;i<=m-1&&obstacleGrid[i][0]==0;i++)
            dp[i][0]=1;


        for(int i=1;i<=m-1;i++)
            for (int j=1;j<=n-1;j++)
            {
                if(obstacleGrid[i][j]==1)
                {
                    continue;
                }
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        return dp[m-1][n-1];
    }
}
