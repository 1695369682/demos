package NewArray;

import org.junit.Test;

import java.util.Arrays;

public class minMoves {

    /**
     * 453. 最小操作次数使数组元素相等
     */
    @Test
    public void test()
    {
        int[] nums=new int[]{1,2,3};

        int minNum = Arrays.stream(nums).min().getAsInt();
        int res = 0;
        for (int num : nums) {
            res += num - minNum;
        }
        System.out.println(res);
    }

    /**
     * 283. 移动零
     */
    @Test
    public void moveZeroes(){
        int[] nums=new int[]{1,2,3};
        int flag=0;
        int zero=0;
        //遇到非0元素就从头开始放，遇到0就标记
        for(int i = 0 ;i < nums.length;i++)
        {
            if(nums[i]!=0)
            {
                nums[flag]=nums[i];
                flag++;
            }
            else{
                zero++;
            }
        }
        //把剩下的元素全都归零
        for(int i=nums.length-1;i>nums.length-zero-1;i--)
        {
            nums[i]=0;
        }
    }
    /**
     * 121. 买卖股票的最佳时机
     */
    @Test
    public void maxProfit() {
        //参数
        int[] prices = new int[] {7, 1, 5, 3, 6, 4};

        //题解
        int res=0;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<prices.length-1;i++){
            if (prices[i]<min)
                min=prices[i];
            else if (prices[i]-min>res)
                res=prices[i]-min;
        }
        System.out.println(res);
    }
    /**
     * 122.买卖股票的最佳时机 II
     */
    @Test
    public void maxProfit2() {
        int[] prices=new int[]{7,1,5,3,6,4};

        int res=0;
        int min=Integer.MAX_VALUE;
        for (int i=0; i<prices.length-1; i++)
        {
            if (prices[i]<min)
                min=prices[i];
            else if (prices[i]-min>0)
            {
                res+=prices[i]-min;
                min=prices[i];
            }
        }
        System.out.println(res);
    }

}
