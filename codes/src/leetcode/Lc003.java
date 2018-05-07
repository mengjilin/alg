package leetcode;

import java.util.HashMap;

public class Lc003 {
    public int lengthOfLongestSubstring(String s) {
        int ret = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; i < s.length(); i++) {
            Integer dup = map.get(s.charAt(i));
            if (dup != null) {
                j = Math.max(j, dup + 1);
            }
            ret = Math.max(ret, i - j + 1);
            map.put(s.charAt(i), i);
        }

        return ret;
    }
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(new Lc003().lengthOfLongestSubstring(s) == 3);

        s = "pwwkew";
        System.out.println(new Lc003().lengthOfLongestSubstring(s) == 3);
    }
}
