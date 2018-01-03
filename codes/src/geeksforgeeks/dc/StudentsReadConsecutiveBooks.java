package geeksforgeeks.dc;

/*
 * Divide and Conquer, from 0 to sum of the pages of all books
 */
public class StudentsReadConsecutiveBooks {

	static int minPages(int[] pages, int studentCount) {
		int sumOfPages = 0;
		for (int p : pages) sumOfPages += p;
		int r = sumOfPages;
		for (int s = 0, t = sumOfPages; s <= t; ) {
			int mid = s + (t-s)/2;
			if (isPossible(pages, studentCount, mid)) {
				if (r > mid) r = mid;
				t = mid -1;
			} else {
				s = mid + 1;
			}
		}
		return r;
	}
	
	static boolean isPossible(int[] pages, int studentCount, int minPages) {
		int requiredStudents = 1;
		int pagesOfCurrentStudent = 0;
		for (int i = 0; i < pages.length; i++) {
			// assign pages to current student as more as possible
			if (pagesOfCurrentStudent + pages[i] <= minPages) { 
				pagesOfCurrentStudent += pages[i];
			} else {
				requiredStudents++;
				pagesOfCurrentStudent = pages[i];
			}
			
			if (pages[i] > minPages || requiredStudents > studentCount)
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		int[] pages = {12, 34, 67, 90};
		System.out.print(113 == minPages(pages, 2));
	}

}
