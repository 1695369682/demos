package src.dynamic.矩阵;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
  每一步只能移动到下一行中相邻的结点上。
 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。

        示例 1：
        输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
        输出：11
        解释：如下面简图所示：
                    2
                   3 4
                  6 5 7
                 4 1 8 3
    自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。*/
public class MinimumTotal {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2); list2.add(3);
        List<Integer> list3 = new ArrayList<>();
        list3.add(1); list3.add(-1); list3.add(-3);
//        List<Integer> list4 = new ArrayList<>();
//        list4.add(4); list4.add(1); list4.add(8); list4.add(3);
        List<List<Integer>> realList = new ArrayList<>();
        realList.add(list); realList.add(list2); realList.add(list3);
//        realList.add(list4);
        System.out.println(minimumTotal(realList));
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int[][] nums = new int[rows][];

        for (int i = 0; i < rows; i++) {
            int cols = triangle.get(i).size();
            nums[i] = new int[cols];

            for (int j = 0; j < cols; j++) {
                nums[i][j] = triangle.get(i).get(j);
            }
        }
        int lines=nums[rows-1].length;
        //dp[i][j]:到达第i,j的最小值
        //状态转移方程
        // dp[i][j]=min(dp[i-1][j]+nums[i+1][j],dp[i-1]+nums[i+1][j+1])
        int[][] dp= new int[rows][lines];
        dp[0][0] =nums[0][0];
        for (int i=1;i<rows;i++)
        {
            int cols=nums[i].length;
            for (int j=0;j<cols;j++)
            {// 初始化dp数组，考虑边界情况
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                } else if (j == cols - 1) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
                }
            }
        }
        Arrays.sort(dp[rows-1]);
        for (int i = 0; i < rows; i++) {
            System.out.println();
            for (int j = 0; j < nums[i].length; j++) {
//                System.out.print("i="+i+"j="+j+"dp[i][j]="+dp[i][j] + " ");
                System.out.print(dp[i][j]+" ");
            }
        }

        return dp[rows-1][0];
    }
}
