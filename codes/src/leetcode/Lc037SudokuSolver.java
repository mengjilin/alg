package leetcode;

import java.util.Arrays;

/*
 * tags: backtracking, prune
 * Time(n!), Space(n^2)
 */
public class Lc037SudokuSolver {
    public static void solveSudoku(char[][] board) {
        int[][] avail = new int[9][9];
        for (int i = 0; i < avail.length; i++) {
            Arrays.fill(avail[i], 0x1ff);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != '.') {
                    put(board, avail, i, j);
                }
            }
        }
        solveSudokuRc(board, avail);
    }

    static boolean solveSudokuRc(char[][] board, int[][] avail) {
        int[] next = selectNext(board, avail);
        if (next[0] == -1) return true; // all done
        int nextAvail = avail[next[0]][next[1]];
        for (int i = 0; i < board.length; i++) {
            if ((nextAvail & (1 << i)) != 0) {
                board[next[0]][next[1]] = (char)(i + '1');
                int[][] availBak = copy(avail);
                put(board, avail, next[0], next[1]);
                if (solveSudokuRc(board, avail)) return true;
                board[next[0]][next[1]] = '.'; // backtracking
                avail = availBak; // restore
            }
        }

        return false;
    }

    static int[][] copy(int[][] a) {
        int[][] ret = new int[a.length][];
        for (int i = 0; i < a.length; i++)
            ret[i] = a[i].clone();
        return ret;
    }

    static void put(char[][] board, int[][] avail, int row, int col) {
        int num = 0x1ff ^ (1 << (board[row][col] - '1'));
        for (int i = 0; i < board.length; i++) {
            avail[i][col] &= num;
            avail[row][i] &= num;
            avail[(row/3)*3 + i/3][(col/3)*3 + i%3] &= num;
        }
    }

    static int[] selectNext(char[][] board, int[][] avail) {
        int[] ret = new int[]{-1, -1};
        int minAvail = 10;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') {
                    int cntAvail = Integer.bitCount(avail[i][j]);
                    if (minAvail > cntAvail) {
                        minAvail = cntAvail;
                        ret[0] = i;
                        ret[1] = j;
                        if (minAvail == 1) return ret;
                    }
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {
                "53..7....".toCharArray(),
                "6..195...".toCharArray(),
                ".98....6.".toCharArray(),
                "8...6...3".toCharArray(),
                "4..8.3..1".toCharArray(),
                "7...2...6".toCharArray(),
                ".6....28.".toCharArray(),
                "...419..5".toCharArray(),
                "....8..79".toCharArray()};
        char[][] exp = new char[][] {
                "534678912".toCharArray(),
                "672195348".toCharArray(),
                "198342567".toCharArray(),
                "859761423".toCharArray(),
                "426853791".toCharArray(),
                "713924856".toCharArray(),
                "961537284".toCharArray(),
                "287419635".toCharArray(),
                "345286179".toCharArray()};
        solveSudoku(board);
        System.out.println(Arrays.deepEquals(exp, board));
    }
}
