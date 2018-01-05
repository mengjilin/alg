package geeksforgeeks.backtracking;

import java.util.Arrays;
import java.util.Stack;
import java.util.TreeSet;

/*
 * Time: O(n!), Space: O(n)
 */
public class Sudoku2 {
	
	///////////////// original //////////////////////////////
	static boolean sudoku(int[][] grid) {
		int[] next = getNext(grid);
		if (next == null) {
			//print(grid);
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

	/////////////////// version 2 /////////////////////////////////////////////////
	static boolean sudoku2(int[][] grid, Node[][] nodes, TreeSet<Node> tree, Stack<Operation> ops) {
		if (tree.isEmpty()) {
			//print(grid);
			return true;
		}
		
		Node node = tree.pollFirst();
		for (int num = 1; num <= N; num++) {
			if (node.safeNums[num-1]) {
				grid[node.i][node.j] = node.num = num;
				update(node, nodes, tree, ops);
				if (sudoku2(grid, nodes, tree, ops)) return true;
				restore(node, nodes, tree, ops);
				grid[node.i][node.j] = node.num = 0;
			}
		}
		
		tree.add(node);
		return false;
	}

	static class Node {
		int i, j;
		int num;
		boolean[] safeNums;
		int safeNumsCnt;
		Node(int i, int j, int num, boolean[] safeNums, int safeNumsCnt) {
			this.i = i;
			this.j = j;
			this.num = num;
			this.safeNums = safeNums;
			this.safeNumsCnt = safeNumsCnt;
		}
		public Node clone() {
			return new Node(i, j, num, safeNums.clone(), safeNumsCnt);
		}
	}
	
	static class Operation {
		int i, j;
		int clearPot;
		Operation(int i, int j, int clearPot) {
			this.i = i;
			this.j = j;
			this.clearPot = clearPot;
		}
	}
	
	static boolean sudoku2(int[][] grid) {
		Node[][] nodes = new Node[N][N];
		TreeSet<Node> tree = new TreeSet<>((a, b) -> 
			a.safeNumsCnt != b.safeNumsCnt ? a.safeNumsCnt - b.safeNumsCnt : a.i*N + a.j - b.i*N - b.j);
		Stack<Operation> ops = new Stack<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				nodes[i][j] = new Node(i, j, grid[i][j], new boolean[N], 0);
				if (grid[i][j] != 0) continue;
				for (int num = 1; num <= N; num++) {
					nodes[i][j].safeNums[num-1] = isSafe(grid, i, j, num);
					if (nodes[i][j].safeNums[num-1]) nodes[i][j].safeNumsCnt++;
				}
				tree.add(nodes[i][j]);
			}
		}
		return sudoku2(grid, nodes, tree, ops);
	}
	
	static void update(Node node, Node[][] nodes, TreeSet<Node> tree, Stack<Operation> ops) {
		ops.add(new Operation(node.i, node.j, -1));
		for (int s = 0; s < N; s++) {
			update(node, nodes[s][node.j], nodes, tree, ops);
			update(node, nodes[node.i][s], nodes, tree, ops);
			update(node, nodes[s/3+node.i/3*3][s%3+node.j/3*3], nodes, tree, ops);
		}
	}

	static void update(Node node, Node target, Node[][] nodes, TreeSet<Node> tree, Stack<Operation> ops) {
		if (target != node && target.safeNums[node.num-1]) {
			tree.remove(target);
			target.safeNums[node.num-1] = false;
			target.safeNumsCnt--;
			tree.add(target);
			ops.add(new Operation(target.i, target.j, node.num-1));
		}
	}
	static void restore(Node node, Node[][] nodes, TreeSet<Node> tree, Stack<Operation> ops) {
		while (!ops.isEmpty()) {
			Operation op = ops.pop();
			if (op.clearPot == -1) return;
			Node target = nodes[op.i][op.j];
			tree.remove(target);
			target.safeNums[op.clearPot] = true;
			target.safeNumsCnt++;
			tree.add(target);
		}
	}
	
	///////////// version 3 ///////////////////////////////////
	static boolean sudoku3(Node[][] nodes) {
		Node node = getNext(nodes);
		if (node == null) {
			//print(nodes);
			return true;
		}
		
		for (int num = 1; num <= N; num++) {
			if (node.safeNums[num-1]) {
				Node[][] copy = newCopy(nodes, node, num);
				if (sudoku3(copy)) return true;
			}
		}
		
		return false;
	}
	
	static Node[][] newCopy(Node[][] nodes, Node curr, int num) {
		Node[][] ret = nodes.clone();
		for (int s = 0; s < N; s++) {
			ret[s] = nodes[s].clone();
		}
		
		ret[curr.i][curr.j] = curr.clone();
		ret[curr.i][curr.j].num = num;
		
		for (int s = 0; s < N; s++) {
			int i = s, j = curr.j;
			if (ret[i][j].num == 0 && ret[i][j].safeNums[num-1]) {
				ret[i][j]= ret[i][j].clone();
				ret[i][j].safeNums[num-1] = false;
				ret[i][j].safeNumsCnt--;
			}
			
			i = curr.i; j = s;
			if (ret[i][j].num == 0 && ret[i][j].safeNums[num-1]) {
				ret[i][j]= ret[i][j].clone();
				ret[i][j].safeNums[num-1] = false;
				ret[i][j].safeNumsCnt--;
			}
			
			i = s/3+curr.i/3*3; j = s%3+curr.j/3*3;
			if (ret[i][j].num == 0 && ret[i][j].safeNums[num-1]) {
				ret[i][j]= ret[i][j].clone();
				ret[i][j].safeNums[num-1] = false;
				ret[i][j].safeNumsCnt--;
			}
		}
		
		return ret;
	}
	
	static Node getNext(Node[][] nodes) {
		Node ret = null;
		for (Node[] row : nodes) {
			for (Node node : row) {
				if (node.num == 0 && (ret == null || ret.safeNumsCnt > node.safeNumsCnt))
					ret = node;
			}
		}
		return ret;
	}
	
	static void print(Node[][] nodes) {
		for (Node[] row : nodes) {
			for (Node node : row)
				System.out.print(node.num + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	static boolean sudoku3(int[][] grid) {
		Node[][] nodes = new Node[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				nodes[i][j] = new Node(i, j, grid[i][j], new boolean[N], 0);
				if (grid[i][j] != 0) continue;
				for (int num = 1; num <= N; num++) {
					nodes[i][j].safeNums[num-1] = isSafe(grid, i, j, num);
					if (nodes[i][j].safeNums[num-1]) nodes[i][j].safeNumsCnt++;
				}
			}
		}
		return sudoku3(nodes);
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 150000; i++) {
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
		//sudoku(grid);
		
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
		sudoku3(grid);
		}
	}

}
