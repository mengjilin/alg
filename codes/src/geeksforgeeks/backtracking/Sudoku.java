package geeksforgeeks.backtracking;

/*
 * Time: O(n!), Space: O(n)
 */
public class Sudoku {

	static boolean sudoku(int[][] grid) {
		int[] next = getNext(grid);
		if (next == null) {
			print(grid);
			return true;
		}
		
		int i = next[0], j = next[1];
		for (int num = 1; num <= N; num++) {
			if (isSafe(grid, i, j, num)) {
				grid[i][j] = num;
				if (sudoku(grid)) return true;
				grid[i][j] = 0;
			}
		}
		
		return false;
	}
	
	static boolean isSafe(int[][] grid, int i, int j, int num) {
		for (int s = 0; s < N; s++) {
			if (grid[s][j] == num || grid[i][s] == num || grid[s/3+i/3*3][s%3+j/3*3] == num)
				return false;
		}
		return true;
	}
	
	static void print(int[][] grid) {
		for (int[] row : grid) {
			for (int i : row)
				System.out.print(i + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	static int[] getNext(int[][] grid) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j] == 0)
					return new int[] {i, j};
			}
		}
		return null;
	}
	
	static final int N = 9;
	public static void main(String[] args) {
		int[][] grid = {
			{3, 0, 6, 5, 0, 8, 4, 0, 0},
	        {5, 2, 0, 0, 0, 0, 0, 0, 0},
	        {0, 8, 7, 0, 0, 0, 0, 3, 1},
	        {0, 0, 3, 0, 1, 0, 0, 8, 0},
	        {9, 0, 0, 8, 6, 3, 0, 0, 5},
	        {0, 5, 0, 0, 9, 0, 6, 0, 0},
	        {1, 3, 0, 0, 0, 0, 2, 5, 0},
	        {0, 0, 0, 0, 0, 0, 0, 7, 4},
	        {0, 0, 5, 2, 0, 6, 3, 0, 0},};
		sudoku(grid);
		
		grid = new int[][] {
			{0,9,4,0,0,1,7,8,0},
			{0,5,0,2,0,0,0,0,3},
			{0,0,2,0,0,0,0,0,0},
			{8,0,0,7,0,4,0,0,0},
			{0,0,5,0,0,0,8,0,0},
			{0,0,0,8,0,9,0,0,7},
			{0,0,0,0,0,0,2,0,0},
			{6,0,0,0,0,5,0,1,0},
			{0,4,7,1,0,0,3,9,0},};
		sudoku(grid);
	}

}
