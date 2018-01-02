package geeksforgeeks.dc;

public class MedianOfTwoArrays {

	static int median(int[] a, int as, int at, int[] b, int bs, int bt) {
		while (bt - bs > 1) {	
			int aMid = (as + at) / 2;
			int bMid = (bs + bt) / 2;
			if (a[aMid] == b[bMid]) return a[aMid];
			if (a[aMid] < b[bMid]) {
				as += bt - bMid;
				bt = bMid;
			} else {
				at -= bMid - bs;
				bs = bMid;
			}
		}
		int m = at - as + 1;
		int aMid = as + (at - as) / 2;
		if (bt == bs) return medianSimple(a, m == 1 ? aMid : aMid-m%2, m == 1 ? aMid : aMid+1, b, bs, bt);
		return medianSimple(a, m == 2 ? aMid : aMid-1, m == 2 ? aMid+1 : aMid+1+(m+1)%2, b, bs, bt);
	}
	
	static int median(int[] a, int[] b) {
		if (a.length < b.length) {
			int[] t = a;
			a = b;
			b = t;
		}
		return median(a, 0, a.length-1, b, 0, b.length-1);
	}
	
	static int medianSimple(int[] a, int as, int at, int[] b, int bs, int bt) {
		int[] c = new int[at-as+1 + bt-bs+1];
		for (int k = 0; k < c.length; k++) {
			if (bs > bt || (as <= bt && a[as] < b[bs])) c[k] = a[as++];
			else c[k] = b[bs++];
		}
		if (c.length % 2 == 1) return c[c.length/2];
		return (c[c.length/2-1] + c[c.length/2]) / 2;
	}
	
	public static void main(String[] args) {
		int[] ar1 = new int[]{1, 12, 15, 26, 38};
		int[] ar2 = new int[]{2, 13, 17, 30, 45};
		System.out.println(16 == median(ar1, ar2));
		
		ar1 = new int[]{1, 12, 15, 26, 38};
		ar2 = new int[]{2, 13};
		System.out.println(13 == median(ar1, ar2));
	}
}
