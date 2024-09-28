package src.hash;

public class isAnagram {
    public static void main(String[] args) {
        System.out.println(isAnagram("hello", "hello"));
    }

    public static boolean isAnagram(String s, String t) {
        int[] hash = new int[26];
        for (int i = 0; i <s.length(); i++) {
            hash[s.charAt(i)-'a']++;
        }
        for (int i = 0; i <t.length(); i++) {
            hash[t.charAt(i)-'a']--;
//            System.out.println(t.charAt(i)-'a');
        }

        for (int i = 0; i < hash.length;i++)
        {
            if (hash[i]!=0)
            {
                return false;
            }
        }
        return true;
    }
}
