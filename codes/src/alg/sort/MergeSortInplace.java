package alg.sort;

import java.util.Arrays;

/*
 * Time(n^2), Space(1)
 * insert each element in array2 into array1 like insert sort.
 */
public class MergeSortInplace {

	static void mergesort(int[] a, int s, int t) {
		if (s >= t) return;
		int m = s + (t-s)/2;
		mergesort(a, s, m);
		mergesort(a, m+1, t);
		merge(a, s, m, t);
	}

	// insert each element in array2 into array1 like insert sort.
	static void merge(int[] a, int s, int m, int t) {
		for (int i = m+1; i <= t; i++) {
			int v = a[i];
			int j = i - 1;
			for (; j >= s && a[j] > v; j--) a[j+1] = a[j];
			a[j+1] = v;
		}
	}

	public static void main(String[] args) {
		int[] a = new int[]{1, 4, 45, 6, 10, -8};
		int[] expected = a.clone();
		mergesort(a, 0, a.length-1);
		Arrays.sort(expected);
		System.out.println(Arrays.equals(a, expected));

		a = new int[]{1, 3, 6, 2, 454, 5, 456, 10, -8};
		expected = a.clone();
		mergesort(a, 0, a.length-1);
		Arrays.sort(expected);
		System.out.println(Arrays.equals(a, expected));
	}
}
