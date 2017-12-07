package geeksforgeeks.array;

public class RotateArray {

	public static void main(String[] args) {
		int[] a = new int[] {1,2,3,4,5,6,7};
		rotate(a, a.length, 2);
		System.out.println(a[0]==3 && a[1]==4 && a[2]==5 && a[3]==6 && a[4]==7 && a[5]==1 && a[6]==2);

	}

	static void rotate(int[] a, int n, int d) {
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
}
