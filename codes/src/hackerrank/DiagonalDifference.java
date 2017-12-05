package hackerrank;

import java.util.*;

public class DiagonalDifference {

	public static void main1(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[][] matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = in.nextInt();
			}
		}
		int result = dDiff(n, matrix);
		System.out.println(result);
	}
	
	static int dDiff(int n, int[][] matrix) {
		int ret = 0;
		for (int i = 0; i < n; i++) {
			ret += matrix[i][i] - matrix[i][n-i-1];
		}
		
		return ret > 0 ? ret : -ret;
	}

	
}
