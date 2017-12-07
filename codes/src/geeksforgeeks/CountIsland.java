package geeksforgeeks;

public class CountIsland {

	public static void main(String[] args) {
		int[][] data = new int[][] {
			{1, 1, 0, 0, 0},
			{0, 1, 0, 0, 1},
			{1, 0, 0, 1, 1},
			{0, 0, 0, 0, 0},
			{1, 0, 1, 0, 1},
		};
		System.out.println(5 == countIsland(data, 5, 5));
	}

	static int countIsland(int[][] a, int m, int n) {
		DisjointSet ds = new DisjointSet(m*n);
		for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) {
			if (a[i][j] == 0) continue;
			if (j > 0 && a[i][j-1] == 1) ds.union(i*n+j, i*n+j-1);
			if (i > 0 && j > 0 && a[i-1][j-1] == 1) ds.union(i*n+j, (i-1)*n+j-1);
			if (i > 0 && a[i-1][j] == 1) ds.union(i*n+j, (i-1)*n+j);
			if (i > 0 && j < n-1 && a[i-1][j+1] == 1) ds.union(i*n+j, (i-1)*n+j+1);
		}
		
		int cnt = 0;
		for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) {
			if (a[i][j] == 1 && ds.find(i*n+j) == i*n+j) cnt++;
		}
		
		return cnt;
	}
}
