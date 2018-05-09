package geeksforgeeks.dp;

/*
 * Time(n*num), Space(num)
 * F(n, num) = Sum(F(n-1, num-i*a[n-1]), i=[0..num/a[n-1])
 */
public class CoinChange {
	
	static int coinChange(int[] coins, int num) {
		int[] sol = new int[num + 1];
		sol[0] = 1;
		for (int i = 0; i < coins.length; i++) {
			for (int s = coins[i]; s <= num; s++) {
				sol[s] += sol[s-coins[i]];
			}
		}
		return sol[num];
	}

	public static void main(String[] args) {
		int[] coins = new int[]{1,2,3};
		System.out.println(4 == coinChange(coins, 4));
		
		coins = new int[]{2,6,3,5};
		System.out.println(5 == coinChange(coins, 10));
	}

}
