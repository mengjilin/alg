package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * tags: backtracking
 * Time(n), Space(n)
 */
public class Lc017LetterCombinationsofaPhoneNumber {
    static String[] mappings = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public static List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return ret;
        lc(ret, digits, 0, "");
        return ret;
    }

    public static void lc(List<String> result, String digits, int cp, String cs) {
        if (cp >= digits.length()) {
            result.add(cs);
            return;
        }

        String map = mappings[digits.charAt(cp) - '0'];
        for (int i = 0; i < map.length(); i++) {
            lc(result, digits, cp + 1, cs + map.charAt(i));
        }
    }

    public static void main(String[] args) {
        List<String> r = letterCombinations("23");
        r.sort(String::compareTo);
        List<String> exp = Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
        System.out.println(exp.equals(r));
    }
}
