using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: backtracking
 */
namespace leetcode
{
    public class Lc051NQueens
    {
        public IList<IList<string>> SolveNQueens(int n)
        {
            var ret = new List<IList<string>>();
            var board = new char[n][];
            for (int i = 0; i < n; i++)
            {
                board[i] = new char[n];
                Array.Fill(board[i], '.');
            }
            //SolveNQueensBt(n, 0, ret, board);
            SolveNQueensBt(n, 1, ret, board, new bool[n], new bool[2 * n], new bool[2 * n]);
            return ret;
        }

        // use one array for diagnonal \
        public void SolveNQueensBt(int n, int next, IList<IList<string>> result, char[][] board, bool[] cols, bool[] diag1, bool[] diag2)
        {
            if (next >= n)
            {
                result.Add(new List<string>(board.Select(r => new string(r)).ToList()));
                return;
            }

            for (int j = 0; j < n; j++)
            {
                int d1 = (diag1.Length + next - j) % diag1.Length;
                int d2 = (diag2.Length + next + j) % diag2.Length;
                if (cols[j] || diag1[d1] || diag2[d2]) continue;

                cols[j] = diag1[d1] = diag2[d2] = true;
                board[next][j] = 'Q';
                SolveNQueensBt(n, next + 1, result, board, cols, diag1, diag2);
                board[next][j] = '.'; // backtracking
                cols[j] = diag1[d1] = diag2[d2] = false;
            }
        }

        public void SolveNQueensBt(int n, int next, IList<IList<string>> result, char[][] board)
        {
            if (next >= n)
            {
                result.Add(new List<string>(board.Select(r => new string(r)).ToList()));
                return;
            }

            for (int j = 0; j < n; j++)
            {
                if (IsSafe(board, next, j))
                {
                    board[next][j] = 'Q';
                    SolveNQueensBt(n, next + 1, result, board);
                    board[next][j] = '.'; // backtracking
                }
            }
        }

        bool IsSafe(char[][] board, int row, int col)
        {
            for (int i = 0; i <= row; i++)
            {
                if (board[i][col] == 'Q') return false;
                if (col >= i && board[row - i][col - i] == 'Q') return false;
                if (col + i < board.Length && board[row - i][col + i] == 'Q') return false;
            }
            return true;
        }

        public void Test()
        {
            var res = SolveNQueens(4);
            foreach (var s in res)
            {
                foreach (var r in s) Console.WriteLine(r);
                Console.WriteLine();
            }
            Console.WriteLine(res.Count);
        }

    }
}
