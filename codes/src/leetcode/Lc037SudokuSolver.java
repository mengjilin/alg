package leetcode;

import java.util.Arrays;

/*
 * tags: backtracking, prune/heuristics
 * Time(n!), Space(n^2)
 */
public class Lc037SudokuSolver {
    public static void solveSudoku(char[][] board) {
        int[][] avail = new int[board.length][board.length];
        for (int i = 0; i < avail.length; i++) {
            Arrays.fill(avail[i], (1 << board.length) - 1);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != '.')
                    put(board, avail, i, j);
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
                board[next[0]][next[1]] = (char) (i + 'A');
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
        int box = (int) Math.sqrt(board.length);
        int num = ((1 << board.length) - 1) ^ (1 << (board[row][col] - 'A'));
        for (int i = 0; i < board.length; i++) {
            avail[i][col] &= num;
            avail[row][i] &= num;
            avail[(row / box) * box + i / box][(col / box) * box + i % box] &= num;
        }
    }

    static int[] selectNext(char[][] board, int[][] avail) {
        int[] ret = new int[]{-1, -1};
        int minAvail = Integer.MAX_VALUE;
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
        long beginTime = System.nanoTime();
        for (int k = 0; k < 5; k++) {
            char[][] board = new char[][]{
                    "EC..G....".toCharArray(),
                    "F..AIE...".toCharArray(),
                    ".IH....F.".toCharArray(),
                    "H...F...C".toCharArray(),
                    "D..H.C..A".toCharArray(),
                    "G...B...F".toCharArray(),
                    ".F....BH.".toCharArray(),
                    "...DAI..E".toCharArray(),
                    "....H..GI".toCharArray()};
            char[][] exp = new char[][]{
                    "ECDFGHIAB".toCharArray(),
                    "FGBAIECDH".toCharArray(),
                    "AIHCDBEFG".toCharArray(),
                    "HEIGFADBC".toCharArray(),
                    "DBFHECGIA".toCharArray(),
                    "GACIBDHEF".toCharArray(),
                    "IFAECGBHD".toCharArray(),
                    "BHGDAIFCE".toCharArray(),
                    "CDEBHFAGI".toCharArray()};
            solveSudoku(board);
            System.out.println(Arrays.deepEquals(exp, board));

            board = new char[][]{
                    "...F.......L......V......".toCharArray(),
                    "............S..U.........".toCharArray(),
                    "...............B.........".toCharArray(),
                    "................W.......I".toCharArray(),
                    ".........................".toCharArray(),
                    "..............H..G.......".toCharArray(),
                    "..............D..........".toCharArray(),
                    ".........................".toCharArray(),
                    "..U...R............FI....".toCharArray(),
                    "...............M.E....W..".toCharArray(),
                    "....T.....H..............".toCharArray(),
                    ".MC...K....I.........R...".toCharArray(),
                    "...Q.....................".toCharArray(),
                    ".H...X...............I...".toCharArray(),
                    "........U................".toCharArray(),
                    ".....S..B................".toCharArray(),
                    "........C......A.........".toCharArray(),
                    "..............W.....A....".toCharArray(),
                    "..H.............F........".toCharArray(),
                    "Y...B..H.................".toCharArray(),
                    "............H.....R...M..".toCharArray(),
                    "E..........S.............".toCharArray(),
                    "..X.............SD.B..H..".toCharArray(),
                    "U........................".toCharArray(),
                    ".Y..I..A.N......J........".toCharArray()};
            exp = new char[][]{
                    "NBQFAGTSHYCLEIJXDMVORKPUW".toCharArray(),
                    "PIDJGCBKLRWXSYMUHAQENOFTV".toCharArray(),
                    "TLRWHEPQIXFKOUVBGCSNDJAMY".toCharArray(),
                    "VCEUKFMNOJBGDHAYWPTRQSLXI".toCharArray(),
                    "XOSYMUVDAWNPQRTKLIFJCEBGH".toCharArray(),
                    "IETBDNXCKOAUMFHPVGWSYLQRJ".toCharArray(),
                    "MQWGFISPEHKYLNDJRTXUOAVCB".toCharArray(),
                    "RSYKLAJVTQOBWPXNCHIDMFUEG".toCharArray(),
                    "HVUNCDRLWMTEJGQOABYFIPKSX".toCharArray(),
                    "OJAPXYUBGFSRVCIMQEKLTDWHN".toCharArray(),
                    "FPIVTJQGRCHMUESDBKOAXYNWL".toCharArray(),
                    "AMCDETKOYSLIBQNFXWUGHRJVP".toCharArray(),
                    "JRKQWHFIDVPAGXOEYNLMSCTBU".toCharArray(),
                    "BHGSUXLMNADWYVCQTRJPFIEKO".toCharArray(),
                    "LNOXYPEWUBRJTKFHISCVGMDAQ".toCharArray(),
                    "DAFCPSOUBIQHRJETNYGKWVXLM".toCharArray(),
                    "GUMEQKWRCDXNFLYAOVBIJHSPT".toCharArray(),
                    "KTJIRMNXFEGVCBWSPLHQAUYOD".toCharArray(),
                    "SWHLVQYTPGUOADKRFJMXBNCIE".toCharArray(),
                    "YXNOBVAHJLMTISPWEUDCKGRQF".toCharArray(),
                    "CGBAJODEQKIFHTLVUXRYPWMNS".toCharArray(),
                    "EDPHOLCFMUYSXWRIKQNTVBGJA".toCharArray(),
                    "QFXMNWIJVPECKOUGSDABLTHYR".toCharArray(),
                    "UKVRSBHYXTJDNAGLMOPWEQIFC".toCharArray(),
                    "WYLTIRGASNVQPMBCJFEHUXODK".toCharArray(),};
            solveSudoku(board);
            System.out.println(Arrays.deepEquals(exp, board));
        }
        System.out.println("Total time: " + ((System.nanoTime() - beginTime) / 1000000D) + " ms");
    }
}
