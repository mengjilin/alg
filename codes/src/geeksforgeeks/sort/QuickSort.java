package geeksforgeeks.sort;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		int[] a = new int[]{1, 4, 45, 6, 10, -8};
		int[] expected = a.clone();
		quicksort(a, 0, a.length-1);
		Arrays.sort(expected);
		System.out.println(0 == Arrays.compare(a, expected));
		
		a = new int[]{1, 3, 6, 2, 454, 5, 456, 10, -8};
		expected = a.clone();
		quicksort(a, 0, a.length-1);
		Arrays.sort(expected);
		System.out.println(0 == Arrays.compare(a, expected));
	}

	// sort the array[s..t], both s and t are inclusive
	static void quicksort(int[] a, int s, int t) {
		if (s < t) {
			int q = partition(a, s, t);
			quicksort(a, s, q-1);
			quicksort(a, q+1, t);
		}
	}
	
	static int partition(int[] a, int s, int t) {
		int v = a[t];
		while (s < t) {
			while (s < t && a[s] < v) s++;
			while (s < t && a[t] > v) t--;
			int tmp = a[s];
			a[s] = a[t];
			a[t] = tmp;
		}
		return s;
	}
}
