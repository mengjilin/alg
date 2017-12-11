package alg;

public class Gcd {

	public static void main(String[] args) {
		System.out.println(gcd(10, 4) == 2);
		System.out.println(gcd(100, 30) == 10);
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
