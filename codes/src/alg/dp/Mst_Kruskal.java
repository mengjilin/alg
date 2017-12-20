package alg.dp;

import java.util.ArrayList;
import java.util.Arrays;

import alg.DisjointSet;
import alg.Test;

public class Mst_Kruskal {

	public static void main(String[] args) {
		int[][] edges = new int[][] {
			{0, 1, 10},
			{0, 2, 6},
			{0, 3, 5},
			{1, 3, 15},
			{2, 3, 4},
		};
		int[][] r = kruskal(4, edges);
		int[][] exp = {{0, 1}, {0, 3}, {2, 3}};
		System.out.println(Test.isSame(r, exp));
		
		edges = new int[][] {
			{0, 1, 4},
		    {0, 7, 8},
		    {1, 2, 12},
		    {1, 7, 11},
		    {2, 3, 7},
		    {2, 8, 2},
		    {2, 5, 4},
		    {3, 4, 9},
		    {3, 5, 14},
		    {4, 5, 10},
		    {5, 6, 2},
		    {6, 7, 1},
		    {6, 8, 6},
		    {7, 8, 7},
		};
		r = kruskal(9, edges);
		exp = new int[][] {
			{0, 1},
			{0, 7},
			{2, 3},
			{2, 5},
			{2, 8},
			{3, 4},
			{5, 6},
			{6, 7},
		};
		System.out.println(Test.isSame(r, exp));
	}
	
	static int[][] kruskal(int n, int[][] edges) {
		ArrayList<int[]> r = new ArrayList<>();
		Arrays.sort(edges, (a, b) -> a[2] - b[2]);
		DisjointSet ds = new DisjointSet(n);
		for (int[] e : edges) {
			int u = ds.find(e[0]);
			int v = ds.find(e[1]);
			if (u != v) {
				r.add(new int[] {e[0], e[1]});
				ds.union(u, v);
			}			
		}
		
		int[][] ret = r.toArray(new int[r.size()][]);
		Arrays.sort(ret, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		return ret;
	}

}
