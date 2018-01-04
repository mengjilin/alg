package alg.backtracking;

public class KnightMove {

	static int[] nextMoveX = {2, 1, -1, -2, -2, -1,  1,  2};
	static int[] nextMoveY = {1, 2,  2,  1, -1, -2, -2, -1};
	static int maxFind = 1;
	static int currFind = 0;
	
	static void knightMove(int n) {
		int[][] steps = new int[n][n];
		steps[0][0] = 1;
		currFind = 0;
		move(steps, 0, 0, 1);
	}
	
	static void move(int[][] steps, int x, int y, int currStep) {
		if (currFind >= maxFind) return;
		if (currStep == steps.length*steps.length) {
			print(steps);
			currFind++;
			return;
		}
		
		for (int i = 0; i < 8; i++) {
			int nx = x+nextMoveX[i];
			int ny = y+nextMoveY[i];
			if (isSafe(steps, nx, ny)) {
				steps[nx][ny] = currStep+1;
				move(steps, nx, ny, currStep+1); // next move
				steps[nx][ny] = 0; // backtracking
			}
		}
	}
	
	static boolean isSafe(int[][] steps, int x, int y) {
		return 0 <= x && x < steps.length &&
			   0 <= y && y < steps.length &&
			   steps[x][y] == 0;
	}
	
	static void print(int[][] steps) {
		for (int[] rows : steps) {
			for (int i : rows) System.out.format("%2d ", i);
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		knightMove(9);
	}

}
