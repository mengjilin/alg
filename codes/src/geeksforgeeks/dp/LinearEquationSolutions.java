package geeksforgeeks.dp;

/*
 * Time(n*num), Space(num)
 * F(n, num) = Sum(F(n-1, num-i*a[n-1]), i=[0..num/a[n-1])
 */
public class LinearEquationSolutions {
	
	static int linearEquationSolutions(int[] coef, int num) {
		int[] sol = new int[num + 1];
		sol[0] = 1;
		for (int i = 0; i < coef.length; i++) {
			for (int s = coef[i]; s <= num; s++) {
				sol[s] += sol[s-coef[i]];
			}
		}
		return sol[num];
	}

	public static void main(String[] args) {
		int[] coef = new int[]{1,2};
		System.out.println(3 == linearEquationSolutions(coef, 5));
		
		coef = new int[]{2,2,3};
		System.out.println(3 == linearEquationSolutions(coef, 4));
	}

}
