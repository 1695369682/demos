package src.notAC;

import java.util.Arrays;

//最后一块石头的重量
public class lastStoneWeight {
    public static void main(String[] args) {
        System.out.println(lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
    }
    public static int lastStoneWeight(int[] stones) {
        int len=stones.length;
        Arrays.sort(stones);

        while (true){
            if (stones.length==0)
            {
                return 0;
            }else if(stones.length==1)
            {return stones[0];}

        }

    }
}
