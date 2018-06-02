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
    public class Lc130SurroundedRegions
    {
        public void Solve(char[,] board)
        {
            int m = board.GetLength(0);
            int n = board.GetLength(1);
            var alive = new bool[m, n];

            for (int i = 0; i < m; i++)
            {
                SetAlive(board, alive, i, 0);
                SetAlive(board, alive, i, n - 1);
            }
            for (int j = 0; j < n; j++)
            {
                SetAlive(board, alive, 0, j);
                SetAlive(board, alive, m - 1, j);
            }

            for (int i = 0; i < m; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if (board[i, j] == 'O' && !alive[i, j])
                        board[i, j] = 'X';
                }
            }
        }

        void SetAlive(char[,] board, bool[,] alive, int row, int col)
        {
            if (row < 0 || row >= board.GetLength(0) || col < 0 || col >= board.GetLength(1) 
                || board[row, col] == 'X' || alive[row, col]) return;
            alive[row, col] = true;
            SetAlive(board, alive, row - 1, col);
            SetAlive(board, alive, row, col - 1);
            SetAlive(board, alive, row + 1, col);
            SetAlive(board, alive, row, col + 1);
        }

        public void Test()
        {
            var board = new char[,] {
                { 'X','X','X','X' },
                { 'X','O','O','X' },
                { 'X','X','O','X' },
                { 'X','X','X','X' },
                { 'X','O','X','X' }, };
            var exp = new char[,] {
                { 'X','X','X','X' },
                { 'X','X','X','X' },
                { 'X','X','X','X' },
                { 'X','X','X','X' },
                { 'X','O','X','X' }, };
            Solve(board);
            Console.WriteLine(exp.SameWith(board));
        }
    }
}

