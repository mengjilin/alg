package geeksforgeeks;

import alg.DisjointSet;

public class CountIsland {

	// use disjoint set, time O(mn), space O(mn)
	static int countIsland(int[][] a) {
		int m = a.length;
		int n = a[0].length;
		DisjointSet ds = new DisjointSet(m*n);
		for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) {
			if (a[i][j] == 0) continue;
			if (j > 0            && a[i][j-1] == 1)   ds.union(i*n+j, i*n+j-1);
			if (i > 0 && j > 0   && a[i-1][j-1] == 1) ds.union(i*n+j, (i-1)*n+j-1);
			if (i > 0            && a[i-1][j] == 1)   ds.union(i*n+j, (i-1)*n+j);
			if (i > 0 && j < n-1 && a[i-1][j+1] == 1) ds.union(i*n+j, (i-1)*n+j+1);
		}
		
		int r = 0;
		for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) {
			if (a[i][j] == 1 && ds.find(i*n+j) == i*n+j) r++;
		}
		
		return r;
	}

	// use dfs, time O(mn), space O(mn)
	static int countIslandDfs(int[][] a) {
		int r = 0;
		int m = a.length;
		int n = a[0].length;
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) {
			if (!visited[i][j] && a[i][j] == 1) {
				dfs(a, m, n, i, j, visited);
				r++;
			}
		}
		return r;
	}
	
	static void dfs(int[][] a, int m, int n, int i, int j, boolean[][] visited) {
		if (visited[i][j]) return;
		visited[i][j] = true;
		if (i > 0 && j > 0     && a[i-1][j-1] == 1) dfs(a, m, n, i-1, j-1, visited);
		if (i > 0              && a[i-1][j] == 1)   dfs(a, m, n, i-1, j, visited);
		if (i > 0 && j < n-1   && a[i-1][j+1] == 1) dfs(a, m, n, i-1, j+1, visited);
		if (j > 0              && a[i][j-1] == 1)   dfs(a, m, n, i, j-1, visited);
		if (j < n-1            && a[i][j+1] == 1)   dfs(a, m, n, i, j+1, visited);
		if (i < m-1 && j >0    && a[i+1][j-1] == 1) dfs(a, m, n, i+1, j-1, visited);
		if (i < m-1            && a[i+1][j] == 1)   dfs(a, m, n, i+1, j, visited);
		if (i < m-1 && j < n-1 && a[i+1][j+1] == 1) dfs(a, m, n, i+1, j+1, visited);
	}
	
	public static void main(String[] args) {
		int[][] data = new int[][] {
			{1, 1, 0, 0, 0},
			{0, 1, 0, 0, 1},
			{1, 0, 0, 1, 1},
			{0, 0, 0, 0, 0},
			{1, 0, 1, 0, 1},
		};
		System.out.println(5 == countIslandDfs(data));
	}
}
