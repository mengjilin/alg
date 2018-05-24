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
    public class Lc079WordSearch
    {
        public bool Exist(char[,] board, string word)
        {
            var used = new bool[board.GetLength(0), board.GetLength(1)];
            for (int i = 0; i < board.GetLength(0); i++)
            {
                for (int j = 0; j < board.GetLength(1); j++)
                {
                    if (SeachBt(board, word, 0, i, j, used)) return true;
                }
            }

            return false;
        }

        bool SeachBt(char[,] board, string word, int next, int row, int col, bool[,] used)
        {
            if (next >= word.Length) return true;

            if (row < 0 || row >= board.GetLength(0) ||
                col < 0 || col >= board.GetLength(1) ||
                used[row, col] || board[row, col] != word[next])
                return false;

            used[row, col] = true;
            if (SeachBt(board, word, next + 1, row - 1, col, used)) return true; // up
            if (SeachBt(board, word, next + 1, row + 1, col, used)) return true; // down
            if (SeachBt(board, word, next + 1, row, col - 1, used)) return true; // left
            if (SeachBt(board, word, next + 1, row, col + 1, used)) return true; // right
            used[row, col] = false;

            return false;
        }

        public void Test()
        {
            var board = new char[,] {
                { 'A', 'B', 'C', 'E'},
                { 'S', 'F', 'C', 'S'},
                { 'A', 'D', 'E', 'E'} };
            Console.WriteLine(Exist(board, "ABCCED") == true);
            Console.WriteLine(Exist(board, "SEE") == true);
            Console.WriteLine(Exist(board, "ABCB") == false);
        }
    }
}

