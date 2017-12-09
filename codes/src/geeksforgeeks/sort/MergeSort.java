package geeksforgeeks.sort;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int[] a = new int[]{1, 4, 45, 6, 10, -8};
		int[] expected = a.clone();
		mergesort(a, 0, a.length-1, new int[a.length]);
		Arrays.sort(expected);
		System.out.println(0 == Arrays.compare(a, expected));
		
		a = new int[]{1, 3, 6, 2, 454, 5, 456, 10, -8};
		expected = a.clone();
		mergesort(a, 0, a.length-1, new int[a.length]);
		Arrays.sort(expected);
		System.out.println(0 == Arrays.compare(a, expected));
	}

	static void mergesort(int[] a, int s, int t, int[] b) {
		if (s >= t) return;
		int m = s + (t-s)/2;
		mergesort(a, s, m, b);
		mergesort(a, m+1, t, b);
		int i = s, j = m+1, k = s;
		while (i <= m && j <= t) {
			if (a[i] < a[j]) b[k++] = a[i++];
			else b[k++] = a[j++];
		}
		while (i <= m) b[k++] = a[i++];
		while (j <= t) b[k++] = a[j++];
		for (; s <= t; s++) a[s] = b[s];
	}
}
