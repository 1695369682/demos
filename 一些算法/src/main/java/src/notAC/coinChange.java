package src.notAC;

import java.util.Arrays;

public class coinChange {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
    }
    public static int coinChange(int[] coins, int amount) {
        int target = 0;
        int result = 0;
        Arrays.sort(coins);
        for (int i=coins.length-1; i>=0;) {
//            System.out.println("i="+i);
            if (target==amount)
                return result;

            if (coins[i]>amount)
            {
//                System.out.println("coin大于amount");
                i--;
            }
            else if (coins[i]+target>amount)
            {
//                System.out.println("coin"+i+"加target大于amount");
                i--;
            }
            else if (target+coins[i]<=amount){
                target += coins[i];
                result++;
//                System.out.println(target);
            }
        }
        return -1;
    }
}
