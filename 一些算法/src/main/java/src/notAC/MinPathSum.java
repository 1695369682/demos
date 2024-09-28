package src.notAC;

public class MinPathSum {

    //给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
    //说明：每次只能向下或者向右移动一步。
    //输入：grid = [[1,3,1],
    //             [1,5,1],
    //             [4,2,1]]
    //输出：7
    //解释：因为路径 1→3→1→1→1 的总和最小。
    public static void main(String[] args) {
        int[][] paths = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        System.out.println("endresult="+minPathSum(paths));
    }
    public static int minPathSum(int[][] grid) {
        int addrow=0;//行-横走
        int addline=0;//列-竖走
        int res=grid[0][0];
        int x=1,y=1;
        while (x<grid.length-1||y<grid[0].length-1)
        {


            if (res+addline>=res+addrow)
            {
                //多少行--竖着
                addline=res+grid[x][0];
                x++;
                res=addline;
            }
            if (res+addline<=res+addrow)
            {
                //每行多少元素--多少列--横着
                addrow=res+grid[0][y];
                y++;
                res=addrow;
            }
            System.out.println(res);
        }
        return res;

    }
}
