package src.dynamic.没通过;

import java.util.Arrays;

public class MinFallingPathSum {
    public static void main(String[] args) {
        int[][] paths = new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        System.out.println(minFallingPathSum(paths));
    }

    public static int minFallingPathSum(int[][] matrix) {


                int m = matrix.length;
                int n = matrix[0].length;
                for (int i = 1; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        // 左边界
                        int left = j == 0 ? j : j - 1;
                        // 右边界
                        int right = j == n - 1 ? j : j + 1;
                        // 找到上一层 j-1、j、j+1 中最小的
                        int min = matrix[i - 1][left];
                        for (int k = left + 1; k <= right; ++k) {
                            min = Math.min(min, matrix[i - 1][k]);
                        }
                        // 原地更新
                        matrix[i][j] += min;
                    }
                }
                return Arrays.stream(matrix[m - 1]).min().getAsInt();
            }
}
