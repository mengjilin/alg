

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
    	test();
    	
    }
    
    static void test() {
        int[][] a = new int[2][2];
        a[0][1] = 3;
        int[][] b = a.clone();
        b[0][1] = 4;
        System.out.println(a[1][1]);
    }
}
