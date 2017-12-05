package hackerrank;

import java.util.Arrays;
import java.util.Scanner;

import javax.swing.DebugGraphics;

public class SherlockMiniMax {

	public static void main1(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] ar = new int[n];
		for (int i = 0; i < n; i++) {
			ar[i] = in.nextInt();
		}
		int p = in.nextInt();
		int q = in.nextInt();
		
		int r = sherlockMiniMax(n, ar, p, q);
		System.out.println(r);
	}
	
	static int sherlockMiniMax(int n, int[] ar, int p, int q) {
		Arrays.sort(ar);
		int r = p, d = 0;
		if (p < ar[0]) d = ar[0] - p;
		for (int i = 1; i < n; i++) {
			int m = ar[i-1] + (ar[i] - ar[i-1]) / 2;
			if (p <= m && m <= q && d < m - ar[i-1]) {
				d = m - ar[i-1];
				r = m;
			}
			else if (ar[i-1] <= q && q <= m && d < q - ar[i-1]) {
				d = q - ar[i-1];
				r = q;
			}
			else if (m <= p && p <= ar[i] && d < ar[i] - p) {
				d = ar[i] - p;
				r = p;
			}
		}
		if (ar[n-1] < q && d < q - ar[n-1]) r = q;
		return r;
	}
	
	public static void test() {
		int n = 3;
		int[] ar = new int[] {5, 14, 8};
		int r = sherlockMiniMax(n, ar, 4, 9);
		System.out.println(r == 4);
		
		n = 100;
		ar = new int[] {635179944,592614358,645156538,601132234,72927588,782907998,26680576,571904512,253411364,368495632,668408894,715988190,473032290,221000496,166917988,579752154,157507364,169860230,693307354,154889188,598650762,721921752,691564182,40331570,680814954,699857994,283203518,248907756,42917082,510182506,103334006,659157584,68613710,41025968,514681540,388857390,732578568,312342822,544403214,414550896,401504698,342138612,578598706,455969120,673917170,671475360,622813896,327454610,742037798,192108990,115056746,453856008,67302432,568454084,178080688,624229470,47759236,7828940,554075052,636698586,56519734,254355714,149844386,684772334,714305610,572611200,740611006,350803732,625347950,27623254,429722502,772950450,508854614,18633596,529333176,635794634,102605328,122897004,595455366,105384508,220658676,370461750,782829740,371224392,595323626,302478768,448101966,213876262,477578452,724776600,623913570,456079206,284937928,441662568,21517112,446207966,467159802,620366990,178426646,130044896};
		r = sherlockMiniMax(n, ar, 64214888,789945206);
		System.out.println(r == 493216533);
	}
	

}
