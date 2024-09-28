package src.Another;


/**
 * leetcode 2525 根据规则将箱子分类
 */
public class CategorizeBox {
    public static void main(String[] args){
        int length=2909;int width=3968;int height=3272;int mass=727;

        long len=length;long wid=width;long hei=height;
        long squ=len*hei*wid*1l;//题不难，考虑一个int类型存储的最大位数问题，转换成long
        System.out.println(squ);
        if(length>=10000||width>=10000||height>=10000||squ>=1000000000)
        {
            if(mass>=100) System.out.println("Both");
            System.out.println("Bulky");
        }
        else if(mass>=100)
        {
            System.out.println("Heavy");
        }
        else
        {
            System.out.println("Neither");
        }

    }

}
