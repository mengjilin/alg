package hackerrank;

public class MagicSquare {

	public static void main1(String[] args) {
		int n=  4;
		int[] matrix = new int[n*n];
		int[] data = new int[n*n];
		magic(matrix, 0, n, data);
	}

	static void magic(int[] matrix, int m, int n, int[] data) {
		for (int d = 0; d < data.length; d++) {
			if (data[d] != 0) continue;
			matrix[m] = d + 1; 
			data[d] = 1; // forward
			if (isValid(matrix, m, n)) magic(matrix, m+1, n, data);
			data[d] = 0; // back
		}
	}
	
	static boolean isValid(int[] matrix, int m, int n) {
		int unit = n*(n*n+1)/2;
		
		// right column
		if (m % n == n - 1) {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				sum += matrix[m-i]; // row
			}
			if (sum != unit) return false;
		}
		
		// bottom row
		if (m >= matrix.length - n) {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				sum += matrix[m-i*n]; // column
			}
			if (sum != unit) return false;
		}
		
		// left bottom corner
		if (m == matrix.length - n) {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				sum += matrix[m-i*n+i]; // diagonal
			}
			if (sum != unit) return false;
		}
		
		// right bottom corner
		if (m == matrix.length - 1) {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				sum += matrix[m-i*n-i]; // diagonal
			}
			if (sum != unit) return false;
			
			// magic
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.format("%d ", matrix[i*n+j]);
				}
				System.out.println();
			}
			System.out.println();
			return false;
		}
		
		if (m % n == n - 1 || m >= matrix.length - n) return true;

		int sum = 0;
		for (int i = 0; i <= m%n; i++) {
			sum += matrix[m-i]; // row
		}
		if (sum >= unit) return false;
		
		sum = 0;
		for (int i = 0; i <= m/n; i++) {
			sum += matrix[m-i*n]; // column
		}
		if (sum >= unit) return false;
		
		return true;
//		switch (m) {
//		case 0:
//			return true;
//		case 1:
//			return matrix[0] + matrix[1] < 15;
//		case 2:
//			return matrix[0] + matrix[1] + matrix[2] == 15;
//		case 3:
//			return matrix[0] + matrix[3] < 15;
//		case 4:
//			return matrix[1] + matrix[4] < 15 && matrix[3] + matrix[4] < 15;
//		case 5:
//			return matrix[2] + matrix[5] < 15 && matrix[3] + matrix[4] + matrix[5] == 15;
//		case 6:
//			return matrix[0] + matrix[3] + matrix[6] == 15 && matrix[2] + matrix[4] + matrix[6] == 15;
//		case 7:
//			return matrix[1] + matrix[4] + matrix[7] == 15;
//		default:// case 8:
//			if (matrix[2] + matrix[5] + matrix[8] == 15 && matrix[0] + matrix[4] + matrix[8] == 15 && matrix[6] + matrix[7] + matrix[8] == 15) {
//				for (int i = 0; i < 9; i += 3) {
//					System.out.format("%d %d %d\n", matrix[i], matrix[i+1], matrix[i+2]);
//				}
//				System.out.println();
//			}
//			return false;
//		}
	}
}
