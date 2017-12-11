package alg.sort;

import java.util.Arrays;

public class MergeSortInplace {

	public static void main(String[] args) {
		int[] a = new int[]{1, 4, 45, 6, 10, -8};
		int[] expected = a.clone();
		mergesort(a, 0, a.length-1);
		Arrays.sort(expected);
		System.out.println(0 == Arrays.compare(a, expected));
		
		a = new int[]{1, 3, 6, 2, 454, 5, 456, 10, -8};
		expected = a.clone();
		mergesort(a, 0, a.length-1);
		Arrays.sort(expected);
		System.out.println(0 == Arrays.compare(a, expected));
	}

	static void mergesort(int[] a, int s, int t) {
		if (s >= t) return;
		int m = s + (t-s)/2;
		mergesort(a, s, m);
		mergesort(a, m+1, t);
		merge(a, s, m, t);
	}

	/* The idea is to begin from last element of ar2[] and search it in ar1[]. 
	 * If there is a greater element in ar1[], then we move last element of ar1[] to ar2[]. 
	 * To keep ar1[] and ar2[] sorted, we need to place last element of ar2[] at correct place in ar1[]. 
	 * We can use Insertion Sort type of insertion for this. */
	static void merge(int[] a, int s, int m, int t) {
		for (int j = t; j >= m+1; j--) {
			int last = a[m];
			int i = m;
			for (; i > s && a[i-1] > a[j]; i--) a[i] = a[i-1];
			if (last > a[j]) {
				a[i] = a[j];
				a[j] = last;
			}
		}
	}
}
