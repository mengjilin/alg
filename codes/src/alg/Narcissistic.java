package alg;

import java.math.BigInteger;

public class Narcissistic {

	public static void main(String[] args) {
		narcissistic(4);
	}

	static void narcissistic(int numdig) {
		powers = new BigInteger[] { 
				BigInteger.valueOf(0).pow(numdig), 
				BigInteger.valueOf(1).pow(numdig),
				BigInteger.valueOf(2).pow(numdig), 
				BigInteger.valueOf(3).pow(numdig), 
				BigInteger.valueOf(4).pow(numdig),
				BigInteger.valueOf(5).pow(numdig), 
				BigInteger.valueOf(6).pow(numdig), 
				BigInteger.valueOf(7).pow(numdig),
				BigInteger.valueOf(8).pow(numdig), 
				BigInteger.valueOf(9).pow(numdig), };
		min = BigInteger.TEN.pow(numdig - 1);
		max = BigInteger.TEN.pow(numdig);

		searchNarc(numdig, 9, BigInteger.ZERO, new int[10]);
	}

	static BigInteger[] powers;
	static BigInteger min;
	static BigInteger max;

	static void searchNarc(int digremaining, int nextdig, BigInteger sum, int[] count) {
		if (nextdig == 0) {
			int[] count2 = new int[10];
			String s = sum.toString();
			for (int i = s.length() - 1; i >= 0; i--) {
				int d = s.charAt(i) - '0';
				count2[d]++;
			}
			for (int i = 1; i <= 9; i++) {
				if (count[i] != count2[i])
					return;
			}
			System.out.println(sum);
			return;
		}
		
		if (sum.add(powers[nextdig].multiply(BigInteger.valueOf(digremaining))).compareTo(min) < 0)
			return;
		
		BigInteger newsum = sum;
		count[nextdig] = 0;
		while (newsum.compareTo(max) < 0 && count[nextdig] <= digremaining) {
			searchNarc(digremaining - count[nextdig], nextdig - 1, newsum, count);
			newsum = newsum.add(powers[nextdig]);
			count[nextdig]++;
		}
	}
}
