package alg;

public class DisjointSet {

	public static void main(String[] args) {
		int[][] friendships = new int[][] {
			{0,1},
			{1,3},
			{2,5},
			{2,8},
			{6,9},
		};
		DisjointSet ds = new DisjointSet(10);
		for (int[] friendship : friendships) ds.union(friendship[0], friendship[1]);
		System.out.println(ds.find(0) == ds.find(3));
	}

	private int[] parent;
//	private int[] ranks;
	
	public DisjointSet(int n) {
		parent = new int[n];
//		ranks = new int[n];
		
		for (int i = 0; i < n; i++) {
			parent[i] = i;
//			ranks[i] = 1;
		}
	}
	
	public int find(int i) {
		if (parent[i] != i) {
			parent[i] = find(parent[i]); // compress path
		}
		return parent[i];
	}
	
	public void union(int i, int j) {
		int ir = find(i);
		int jr = find(j);
		parent[ir] = jr;
		
		// use ranks to improve efficiency
//		if (ir == jr) return;
//		if (ranks[ir] < ranks[jr]) parent[ir] = jr;
//		else if (ranks[ir] > ranks[jr]) parent[jr] = ir;
//		else {
//			parent[ir] = jr;
//			ranks[jr]++;
//		}
	}
}
