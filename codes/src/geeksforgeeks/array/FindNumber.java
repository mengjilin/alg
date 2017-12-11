package geeksforgeeks.array;

public class FindNumber {

	public static void main(String[] args) {
		int[] a = new int[] {5, 6, 7, 8, 9, 10, 1, 2, 3};
		int r = findNumber(a, 3);
		int expected = 8;
		System.out.println(r == expected);
		
		a = new int[] {4, 5, 6, 7, 8, 9, 1, 2, 3};
		r = findNumber(a, 6);
		expected = 2;
		System.out.println(r == expected);
	}

	// find a number in a sorted and rotated array
	static int findNumber(int[] a, int v) {
		int i = 0, j = a.length-1; // i and j inclusive
		while (i <= j) {
			int m = i + (j-i+1)/2;
			if (a[m] == v) return m;
			if (a[i] <= a[m]) { // left is sorted
				if (a[i] <= v && v <= a[m]) j = m - 1;
				else i = m + 1;
			} else { // right is sorted
				if (a[m] <= v && v <= a[j]) i = m + 1;
				else j = m - 1;
			}
		}
		return -1;
	}
}
