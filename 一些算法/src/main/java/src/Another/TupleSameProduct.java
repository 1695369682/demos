package src.Another;

import java.util.HashMap;
import java.util.Map;


/**
 * 1726. 同积元组
 */

public class TupleSameProduct {

    public static void main(String[] args) {
        int[] nums=new int[]{2,3,4,6,8,12};

        int res=0;
        Map<Integer,Integer> m=new HashMap();
        //key存储乘积，value存储出现的次数
        //map的特性：key不重复，以及containsKey方法可以查看是否存在这个数
        for(int i=0;i<nums.length;i++)
        {
            for(int j=i+1;j<nums.length;j++)
            {
                if(m.containsKey(nums[i]*nums[j]))
                //如果map中存在这个数，对value进行处理
                {
                    res++;
                    m.put(nums[i]*nums[j],m.get(nums[i]*nums[j])+1);
                }
                if(!m.containsKey(nums[i]*nums[j]))
                //不存在直接put进即可
                {
                    m.put(nums[i]*nums[j],1);
                }
            }
        }
        //遍历map，将一个乘积出现三次及以上的value处理
        for (int key:m.keySet()){
            int i = m.get(key);
                while(i-2>0){
                    res+=i-2;
                    i--;
                }
        }
        //结果*8，八组，根据题目要求来
        System.out.println(res*8);
    }

}
