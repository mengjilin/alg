package leetcode;

import java.util.*;

/*
 * tags: two pointers, hash, sliding window
 * Time(n), Space(m)
 */
public class Lc030SubstringwithConcatenationofAllWords {
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ret = new LinkedList<>();
        if (s.isEmpty() || words.length == 0) return ret;
        // same length? Yes
        int len = words[0].length();
        HashMap<String, Integer> mapTarget = new HashMap<>();
        for (String w : words) {
            if (mapTarget.containsKey(w)) mapTarget.put(w, mapTarget.get(w) + 1);
            else mapTarget.put(w, 1);
        }

        for (int start = 0; start < len; start++) {
            HashMap<String, Integer> map = new HashMap<>();
            for (int lo = start, hi = start; hi <= s.length() - len; hi += len) {
                String w = s.substring(hi, hi + len);
                if (mapTarget.containsKey(w)) {
                    if (!map.containsKey(w)) {
                        map.put(w, 1);
                    } else if (map.get(w) < mapTarget.get(w)) {
                        map.put(w, map.get(w) + 1);
                    } else { // move the left side of the window until the extra word
                        while (lo < hi) {
                            lo += len;
                            String w1 = s.substring(lo - len, lo);
                            if (w1.equals(w)) break;
                            map.put(w1, map.get(w1) - 1);
                        }
                    }

                    if (hi - lo == (words.length - 1) * len)
                        ret.add(lo);
                } else { // invalid word, move the window
                    map.clear();
                    lo = hi + len;
                }
            }
        }

        return ret;
    }

    // Time(nm)
    public List<Integer> findSubstringDirect(String s, String[] words) {
        List<Integer> ret = new LinkedList<>();
        if (s.isEmpty() || words.length == 0) return ret;
        // same length?
        int len = words[0].length();

        for (int i = 0; i <= s.length() - words.length * len; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            for (String w : words) {
                Integer v = map.get(w);
                if (v == null) map.put(w, 1);
                else map.put(w, v + 1);
            }

            for (int j = 0; j < words.length; j++) {
                String w = s.substring(i + j*len, i + j*len + len);
                Integer v = map.get(w);
                if (v == null || v == 0) break;
                map.put(w, v - 1);
                if (j == words.length - 1) ret.add(i);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = new String[]{"foo","bar"};
        List<Integer> exp = Arrays.asList(0, 9);
        System.out.println(exp.equals(findSubstring(s, words)));

        s = "";
        words = new String[]{};
        exp = Arrays.asList();
        System.out.println(exp.equals(findSubstring(s, words)));

        s = "wordgoodgoodgoodbestword";
        words = new String[]{"word","good","best","good"};
        exp = Arrays.asList(8);
        System.out.println(exp.equals(findSubstring(s, words)));

        s = "wordgoodgoodgoodbestword";
        words = new String[]{"word","good","best","word"};
        exp = Arrays.asList();
        System.out.println(exp.equals(findSubstring(s, words)));
    }
}
