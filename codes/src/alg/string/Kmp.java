package alg.string;

/*
 * Time(n+m), Space(m)
 * build the longest proper suffix against the pattern string, then use the same to match the source
 */
public class Kmp {

    static int search(String pattern, String s) 
    {
    	// TODO: check null and empty   	
        int[] lps = buildLps(pattern);
        int pi = 0;  // index for pattern
        for (int i = 0; i < s.length();) {        	
            if (pattern.charAt(pi) == s.charAt(i)) {
                pi++;
                i++;
            } else if (pi > 0) {
            	pi = lps[pi-1];
            } else {
            	i++;
            }
            
        	if (pi == pattern.length()) return i - pi;
        }
        
        return -1;
    }
 
    // get the array of longest proper suffix
    static int[] buildLps(String s) 
    {
    	int[] lps = new int[s.length()];
    	
        // index of the previous longest prefix suffix
        int pi = 0;
 
        for (int i = 1; i < s.length();) {
            if (s.charAt(i) == s.charAt(pi)) {
                pi++;
                i++;
                lps[i-1] = pi;
            } else if (pi > 0) {
            	pi = lps[pi-1];
            } else {
                i++;
            }
        }
        
        return lps;
    }

    /*
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (needle.length() > haystack.length() || haystack.length() == 0) return -1;
        char[] ndl = needle.toCharArray();
        char[] hay = haystack.toCharArray();
        int[] pai = new int[ndl.length];
        pai[0] = -1;
        for (int i = 1, k = -1; i < ndl.length; i++) {
            while (k > -1 && ndl[k + 1] != ndl[i]) k = pai[k];
            pai[i] = ndl[k + 1] == ndl[i] ? ++k : k;
        }
        for (int i = 0, k = -1; i < hay.length; i++) {
            while (k > -1 && ndl[k + 1] != hay[i]) k = pai[k];
            if (ndl[k + 1] == hay[i] && ++k == ndl.length - 1) return i - k;
        }
        return -1;
    }
     */
    public static void main(String[] args) {
        String s = "ABABDABACDABABCABAB";
        String p = "ABABCABAB";
        System.out.println(10 == search(p, s));

        s = "AAACABAAAABA";
        p = "AAAA";
        System.out.println(6 == search(p, s));

        s = "mississippi";
        p = "issip";
        System.out.println(4 == search(p, s));
    }
}
