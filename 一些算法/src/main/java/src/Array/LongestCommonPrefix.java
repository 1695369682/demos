package src.Array;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs=new String[]{"flower","flow","flight"};

        System.out.println(strs[0].charAt(0));
        List<String> lst=new ArrayList<String>();
        int i=0;
        while (i<strs[0].length()){
            lst.add(String.valueOf(strs[0].charAt(i)));
            for (int j=0;j<strs.length-1;j++){
                if (!String.valueOf(strs[j].charAt(i)).equals(lst.get(i))){
                    break;
                }
            }
            i++;
        }
        System.out.println(lst.toString());
    }
}
