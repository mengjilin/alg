package alg.backtracking;

import java.util.Arrays;
import java.util.Collections;

public class Permutation {

	/*
	 * Time(n), Space(1), see stl::next_permutation
	 */
	static boolean next_permutation(char[] a) {
	    if (a == null || a.length <= 1) return false;
	    int first = 0, last = a.length - 1;
	    for (int i = last; i > first; i--) {
	        if (a[i-1] < a[i]) { // find the first one (i-1) in natural order from last
	            int j = last;
	            while (a[i-1] >= a[j]) j--;
	            swap(a, i-1, j);
	            reverse(a, i, last);
	            return true;
	        }
	    }
	    
        reverse(a, first, last);
        return false;
	}
	
	static void permutation(String s) {
		char[] a = s.toCharArray();
		Arrays.sort(a);
		do {
			System.out.println(new String(a));			
		} while (next_permutation(a));
	}
	
	/*
	 * Time(n), Space(n), can't handle duplicates
	 * recursive
	 */
	static void permutation2(char[] a, int s, int t) {
		if (s == t) {
			System.out.println(new String(a));
			return;
		}
		
		for (int i = s; i <= t; i++) {
			swap(a, s, i);
			permutation2(a, s+1, t);
			swap(a, s, i);
		}
	}
	
	static void swap(char[] a, int i, int j) {
		char t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	static void reverse(char[] a, int i, int j) {
		for (; i < j; i++, j--) 
			swap(a, i, j);
	}
	
	static void permutation2(String s) {
		permutation2(s.toCharArray(), 0, s.length() - 1);
	}
	
	////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		permutation("abcde");
	}

}
