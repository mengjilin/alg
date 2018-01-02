package alg.dp;

import java.util.*;

/*
 * 1. add all vertices to a minimum heap
 * 2. loop until heap is empty, extract min from heap and update the distance of its all adjacent vertices.
 */
public class Sp_Dijkstra {
	
	static int[] dijkstra(int n, int[][] edges) {
		Graph g = new Graph();
		g.n = 9;
		g.edges = new Edge[n];
		for (int i = 0; i < edges.length; i++) {
			Edge e = new Edge(edges[i][1], edges[i][2]);
			e.next = g.edges[edges[i][0]];
			g.edges[edges[i][0]] = e;
			
			e = new Edge(edges[i][0], edges[i][2]);
			e.next = g.edges[edges[i][1]];
			g.edges[edges[i][1]] = e;
		}
		int[] dist = new int[g.n];
		for (int i = 1; i < g.n; i++) dist[i] = INF;
		
		TreeSet<Node> q = new TreeSet<>((a, b) -> a.d == b.d ? a.v - b.v : a.d - b.d);
		q.add(new Node(0, 0));
		for (int i = 1; i < g.n; i++) {
			q.add(new Node(i, INF));
		}
		
		while (!q.isEmpty()) {
			Node node = q.pollFirst();
			for (Edge e = g.edges[node.v]; e != null; e = e.next) {
				if (dist[e.v] > dist[node.v] + e.w) {
					Node tmp = new Node(e.v, dist[e.v]);
					dist[e.v] = dist[node.v] + e.w;
					boolean test = q.remove(tmp);
					tmp.d = dist[e.v];
					q.add(tmp);
				}
			}
		}
		
		return dist;
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
		int[] r = dijkstra(9, edges);
		int[] expected = new int[] {0, 4, 15, 22, 21, 11, 9, 8, 15};
		System.out.println(Arrays.compare(r, expected) == 0);
	}
}