

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
    	//test();
    	//hackerrank.MagicSquare.main1(args);
    	alg.Kmp.main1(args);
    	
    }
    
    static void test() {
    	int[] a = new int[] {2,5,3};
    	Arrays.sort(a);
    	int f = Arrays.binarySearch(a, 4);
    	System.out.println(f);
    }
    
}
