package alg.sort;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int[] a = new int[]{1, 4, 45, 6, 10, -8};
		int[] expected = a.clone();
		mergesort(a, 0, a.length-1, new int[a.length]);
		Arrays.sort(expected);
		System.out.println(Arrays.equals(a, expected));
		
		a = new int[]{1, 3, 6, 2, 454, 5, 456, 10, -8};
		expected = a.clone();
		mergesort(a, 0, a.length-1, new int[a.length]);
		Arrays.sort(expected);
		System.out.println(Arrays.equals(a, expected));
	}

	static void mergesort(int[] a, int s, int t, int[] b) {
		if (s >= t) return;
		int m = s + (t-s)/2;
		mergesort(a, s, m, b);
		mergesort(a, m+1, t, b);
		merge(a, s, m, t, b);
	}
	
	static void merge(int[] a, int s, int m, int t, int[] b) {
		for (int i = s; i <= t; i++) b[i] = a[i];
		for (int i = s, j = m+1, k = s; k <= t; k++) {
			if (j > t || (i <= m && b[i] < b[j])) a[k] = b[i++];
			else a[k] = b[j++];
		}
	}
}
