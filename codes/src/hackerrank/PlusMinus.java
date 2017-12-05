package hackerrank;

import java.util.Scanner;

public class PlusMinus {

	public static void main1(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] ar = new int[n];
		for (int i = 0; i < n; i++) {
			ar[i] = in.nextInt();
		}
		
		int[] result = plusMinus(ar);
		for (int i = 0; i < result.length; i++) {
			System.out.printf("%.6f\n", (double)result[i] / n);
		}
	}
	
	static int[] plusMinus(int[] ar) {
		int[] result = new int[3];
		for (int i : ar) {
			if (i > 0) result[0]++;
			else if (i < 0) result[1]++;
			else result[2]++;
		}
		
		return result;
	}

}
