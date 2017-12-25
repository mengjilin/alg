package geeksforgeeks.greedy;

import java.util.TreeSet;

public class ConnectRopes {

	public static void main(String[] args) {
		int[] ropes = new int[] {4,3,2,6};
		int r = connectRopes(ropes);
		int exp = 29;
		System.out.print(r == exp);
	}
	
	static int connectRopes(int[] ropes) {
		int r = 0;
		TreeSet<Integer> heap = new TreeSet<>();
		for (int i : ropes) {
			heap.add(i);
		}
		while (heap.size() > 1) {
			int a = heap.pollFirst();
			int b = heap.pollFirst();
			heap.add(a + b);
			r += a + b;
		}
		return r;
	}

}
