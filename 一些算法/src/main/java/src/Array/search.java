package src.Array;

public class search {
    public static void main(String[] args) {
        System.out.println(search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
    }
    public static int search(int[] nums, int target) {
        int start=0;
        int end=nums.length-1;
        int mid=0;
        while (true)
        {
            mid =(start+end)/2;
            if (nums[mid]==target)
                return mid;
            if (start>end)
                return -1;
            if (nums[mid]>target)
                end=mid-1;
            if (nums[mid]<target)
                start=mid+1;
        }
    }
}
