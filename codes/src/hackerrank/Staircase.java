package hackerrank;

import java.util.Scanner;

public class Staircase {

	public static void main1(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		staircase(n);
	}
	
	static void staircase(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - i - 1; j++)
				System.out.print(' ');
			for (int j = 0; j <= i; j++)
				System.out.print('#');
			System.out.println();
		}
	}

}
