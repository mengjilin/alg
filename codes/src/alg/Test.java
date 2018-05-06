package alg;
import java.util.Arrays;

public class Test {
	public static boolean equals(int[][] a, int[][] b) {
		if (a == null && b == null) return true;
		if (a == null || b == null) return false;
		
		boolean same = a.length == b.length;
		for (int i = 0; i < a.length && same; i++) {
			same &= Arrays.equals(a[i], b[i]);
		}
		
		return same;
	}
}
