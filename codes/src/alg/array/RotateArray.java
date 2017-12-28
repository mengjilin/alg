package alg.array;

import java.util.Arrays;
import java.util.List;

public class RotateArray {
	
	// see java.util.Collections.rotate
    static <T> void rotate(T[] list, int distance) {
    	int size = list.length;
        if (size == 0)
            return;
        distance = distance % size;
        if (distance < 0)
            distance += size;
        if (distance == 0)
            return;

        for (int cycleStart = 0, nMoved = 0; nMoved != size; cycleStart++) {
            T displaced = list[cycleStart];
            int i = cycleStart;
            do {
                i += distance;
                if (i >= size)
                    i -= size;
                T temp = list[i];
                list[i] = displaced;
                displaced = temp;
                nMoved ++;
            } while (i != cycleStart);
        }
    }

	public static void main(String[] args) {
		Integer[] a = new Integer[] {1,2,3,4,5,6,7};
		rotate(a, 2);
		Integer[] expected = new Integer[] {6,7,1,2,3,4,5}; 
		System.out.println(0 == Arrays.compare(a, expected));

	}
}
