package src.greed;

import java.util.Arrays;

public class findContentChildren {
    public static void main(String[] args) {
        System.out.println(findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));
    }
    public static int findContentChildren(int[] g, int[] s) {
        //孩子
        Arrays.sort(g);
        //饼干
        Arrays.sort(s);
        int result=0;
        int index=s.length-1;
        for(int i=g.length-1;i>0;i--)
            if(s[index]>=g[i])
            {
                index--;
                result++;
            }
        return result;
    }
}
