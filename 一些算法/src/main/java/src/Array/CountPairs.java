package src.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountPairs {
    public static void main(String[] args) {
        int n=7;int[][] edges=new int[][]{{0,2},{0,5},{2,4},{1,6},{5,4}};

                List<Integer>[] g = new ArrayList[n];
                Arrays.setAll(g, e -> new ArrayList<>());
                for (int[] e : edges) {
                    int x = e[0], y = e[1];
                    g[x].add(y);
                    g[y].add(x); // 建图
                }

                boolean[] vis = new boolean[n];
                long ans = 0;
                for (int i = 0, total = 0; i < n; i++) {
                    if (!vis[i]) { // 未访问的点：说明找到了一个新的连通块
                        int size = dfs(i, g, vis);
                        ans += (long) size * total;
                        total += size;
                    }
                }
                System.out.println(ans);
            }

    private static int dfs(int x, List<Integer>[] g, boolean[] vis) {
        vis[x] = true; // 避免重复访问同一个点
        int size = 1;
        for (int y : g[x]) {
            if (!vis[y]) {
                size += dfs(y, g, vis);
            }
        }
        return size;
    }

}
