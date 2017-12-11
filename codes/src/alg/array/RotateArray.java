package alg.array;

import java.util.Arrays;

public class RotateArray {

	public static void main(String[] args) {
		int[] a = new int[] {1,2,3,4,5,6,7};
		rotate(a, a.length, 2);
		int[] expected = new int[] {3,4,5,6,7,1,2};
		System.out.println(0 == Arrays.compare(a, expected));

	}
	
	static void rotate(int[] a, int n, int d) {
		d = (d%n + n) % n;
		for (int i = 0, s = 0; i < n; i++, s++) {
			int temp = a[s];
			int k, kn;
			for (k = s; (kn = (k+d)%n) != s; k = kn) {
				a[k] = a[kn];
				i++;
			}
			a[k] = temp;
		}
	}

	static void leftRotate(int[] a, int n, int d) {
		int g = gcd(n, d);
		for (int i = 0; i < g; i++) {
			int temp = a[i];
			int k, kn;
			for (k = i; (kn = (k+d)%n) != i; k = kn) {
				a[k] = a[kn];
			}
			a[k] = temp;
		}
	}
	
	static int gcd(int a, int b) {
		while (b > 0) {
			int t = b;
			b = a % b;
			a = t;
		}
		return a;
	}

	static void leftRotate2(int[] a, int n, int d) {
		reverse(a, 0, d-1);
		reverse(a, d, n-1);
		reverse(a, 0, n-1);
	}
	
	// reverse the array from startIndex (inclusive) to endIndex (inclusive)
	static void reverse(int[] a, int startIndex, int endIndex) {
		for (; startIndex < endIndex; startIndex++, endIndex--) {
			int t = a[startIndex];
			a[startIndex] = a[endIndex];
			a[endIndex] = t;
		}
	}
}
