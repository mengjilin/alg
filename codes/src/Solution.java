

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
    	test();
    	//hackerrank.MagicSquare.main1(args);
    	//alg.Kmp.main(args);
    	//geeksforgeeks.array.CountEmployees.main(args);
    	
    }
    
    static void test() {
		TreeSet<int[]> r = new TreeSet<>((a, b) -> 
		Math.min(a[0], a[1]) != Math.min(b[0], b[1]) ? 
		Math.min(a[0], a[1]) - Math.min(b[0], b[1]) : 
		Math.max(a[0], a[1]) - Math.max(b[0], b[1]));

		r.add(new int[] {1,3});
		r.add(new int[] {3,2});
		r.add(new int[] {3,1});
		int[][] a = r.toArray(new int[r.size()][2]);
    	System.out.println(a);
    }
    
}
