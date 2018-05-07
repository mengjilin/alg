package alg.string;

public class Kmp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        String s = "ABABDABACDABABCABAB";
        String p = "ABABCABAB";
        System.out.println(10 == search(p, s));
        
        s = "AAACABAAAABA";
        p = "AAAA";
        System.out.println(6 == search(p, s));
	}

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
 
    // get the array of longest prefix suffix
    static int[] buildLps(String s) 
    {
    	int[] lps = new int[s.length()];
    	
        // index of the previous longest prefix suffix
        int pi = 0;
 
        for (int i = 1; i < s.length();) {
            if (s.charAt(i) == s.charAt(pi)) {
                lps[i++] = ++pi;
            } else if (pi > 0) {
            	pi = lps[pi-1];
            } else {
                i++;
            }
        }
        
        return lps;
    }
}
