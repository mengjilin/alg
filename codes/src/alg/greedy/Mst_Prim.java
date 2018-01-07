package alg.greedy;

import java.util.Arrays;
import java.util.TreeSet;

import alg.Test;

/*
 * Time(ElogE), Space(V)
 * 1. add all vertices to a minimum heap
 * 2. loop until heap is empty, extract min from heap to mst and update the distance of its all adjacent vertices.
 */
public class Mst_Prim {

	static int[][] prim(int n, int[][] edges) {
		Graph g = new Graph();
		g.n = n;
		g.edges = new Edge[n];
		for (int i = 0; i < edges.length; i++) {
			Edge e = new Edge(edges[i][1], edges[i][2]);
			e.next = g.edges[edges[i][0]];
			g.edges[edges[i][0]] = e;
			
			e = new Edge(edges[i][0], edges[i][2]);
			e.next = g.edges[edges[i][1]];
			g.edges[edges[i][1]] = e;
		}
		
		Node[] nodes = new Node[n];
		boolean[] nodesPolled = new boolean[n]; 
		TreeSet<Node> q = new TreeSet<>((a, b) -> a.d == b.d ? a.v - b.v : a.d - b.d);
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i, INF);
			q.add(nodes[i]);
		}
		nodes[0].d = 0;
		
		Node[] r = new Node[n];
		int rc = 0;
		while (!q.isEmpty()) {
			Node node = r[rc++] = q.pollFirst();
			nodesPolled[node.v] = true;
			for (Edge e = g.edges[node.v]; e != null; e = e.next) {
				if (nodes[e.v].d > e.w && !nodesPolled[e.v]) {
					q.remove(nodes[e.v]);
					nodes[e.v].d = e.w;
					nodes[e.v].u = node.v;
					q.add(nodes[e.v]);
				}
			}
		}
		
		int[][] ret = new int[n-1][2];
		for (int i = 1; i < r.length; i++) {
			ret[i-1][0] = Math.min(r[i].u, r[i].v);
			ret[i-1][1] = Math.max(r[i].u, r[i].v);
		}
		Arrays.sort(ret, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		return ret;
	}
	
	static int INF = Integer.MAX_VALUE / 2;
	static class Graph {
		int n;
		Edge[] edges;
	}
	
	static class Edge {
		int v;
		int w;
		Edge next;
		Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	
	static class Node {
		int u, v;
		int d; // distance from the selected set
		Node(int v, int d) {
			this.v = v;
			this.d = d;
		}
	}

	public static void main(String[] args) {
		int[][] edges = new int[][] {
			{0, 1, 10},
			{0, 2, 6},
			{0, 3, 5},
			{1, 3, 15},
			{2, 3, 4},
		};
		int[][] r = prim(4, edges);
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
		r = prim(9, edges);
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
}
