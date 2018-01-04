package geeksforgeeks.backtracking;

public class Queens {
	
	static void solveQueens(int n) {
		int[][] board = new int[n][n];
		queen2(board);
	}
	
	static boolean queen(int[][] board, int i) {
		if (i == board.length) {
			print(board);
			return true;
		}
		
		for (int j = 0; j < board.length; j++) {
			if (isSafe(board, i, j)) {
				board[i][j] = 1;
				if (queen(board, i+1)) return true;
				board[i][j] = 0; // backtracking
			}
		}
		
		return false;
	}
	
	static boolean queen2(int[][] board) {
		for (int i = 0; 0 <= i && i < board.length; ) {
			boolean placed = false;
			
			int prevj = 0;
			while (prevj < board.length && board[i][prevj] == 0) prevj++;
			if (prevj == board.length) prevj = -1;
			else board[i][prevj] = 0; // backtracking
			
			for (int j = prevj+1; j < board.length; j++) {
				if (isSafe(board, i, j)) {
					board[i][j] = 1; // move next
					placed = true;
					break; 
				}
			}
			if (placed) i++;
			else i--;
			
			if (i == board.length) {
				print(board);
				return true;
			}
		}
		
		return false;
	}
	
	static boolean isSafe(int[][] board, int row, int col) {
		for (int i = 0; i < row; i++)
			if (board[i][col] == 1) return false;
		
		for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--)
			if (board[i][j] == 1) return false;
		
		for (int i = row-1, j = col+1; i >= 0 && j < board.length; i--, j++)
			if (board[i][j] == 1) return false;
		
		return true;
	}
	
	static void print(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		solveQueens(5);
	}

}
