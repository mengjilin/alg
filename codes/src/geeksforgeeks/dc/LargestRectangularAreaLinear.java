package geeksforgeeks.dc;

import java.util.Stack;

public class LargestRectangularAreaLinear {
	
	static int largestRectangularArea(int[] bars) {
		int r = 0;
		Stack<Integer> st = new Stack<>();
		for (int i = 0; i < bars.length || !st.isEmpty(); ) {
			if (st.isEmpty() || (i < bars.length && bars[st.peek()] <= bars[i])) {
				st.push(i++);
			} else {
				int mp = st.pop();
				int areawithTop = bars[mp] * (st.isEmpty() ? i : i - st.peek() - 1);
				if (r < areawithTop)
					r = areawithTop;
			}
		}
		return r;
	}
	
	public static void main(String[] args) {
		int[] bars = {6, 1, 5, 4, 5, 2, 6};
		System.out.println(12 == largestRectangularArea(bars));
	}

}
