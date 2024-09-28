package src.greed;

public class wiggleMaxLength {
    public static void main(String[] args) {
        System.out.println(wiggleMaxLength(new int[]{1,1,7,4,9,2,5}));
    }
    public static int wiggleMaxLength(int[] nums) {
        if(nums.length==1)
        {
            return 1;
        }
        if (nums.length == 2) {
            if  (nums[1]==nums[0])
                return 1;
        }
        int result = 0;
        int i=0;
        boolean flag=true;
        for (i=1; i<nums.length; i++)
        {
            if(nums[i]!=nums[i-1])
            {
                flag=nums[i]-nums[i-1]>0?true:false;
                break;
            }
            if(i==nums.length-1)
                return 1;

        }
        System.out.println(i+"::"+flag);
        for (i=i+1; i<nums.length; i++) {
            System.out.println(i+"::"+flag);
            if (nums[i]>nums[i-1]&&flag==false)
            {
                result++;
                flag=!flag;
            }
            if (nums[i]<nums[i-1]&&flag==true)
            {
                result++;
                flag=!flag;
            }

        }
        return result+2;
    }
}
