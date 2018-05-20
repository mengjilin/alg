using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: number
 */
namespace leetcode
{
    public class Lc065ValidNumber
    {
        public bool IsNumber(string s)
        {
            int i = 0;
            
            // optinal leading space
            while (i < s.Length && s[i] == ' ') i++;

            // optinal sign
            if (i < s.Length && (s[i] == '+' || s[i] == '-')) i++;

            // base number integral part, optional if has a period
            bool hasNumber = false;
            while (i < s.Length && ('0' <= s[i] && s[i] <= '9')) { i++; hasNumber = true; }

            // optinal period
            var hasPeriod = i < s.Length && s[i] == '.';
            if (hasPeriod)
            {
                i++;

                // base number fractional part, optional if has integral part
                bool hasFraction = false;
                while (i < s.Length && ('0' <= s[i] && s[i] <= '9')) { i++; hasFraction = true; }
                if (!hasNumber && !hasFraction) return false;
            }

            if (!hasNumber && !hasPeriod) return false;

            // exponential 
            var hasExp = i < s.Length && (s[i] == 'e' || s[i] == 'E');
            if (hasExp)
            {
                i++;

                // optinal sign
                if (i < s.Length && (s[i] == '+' || s[i] == '-')) i++;

                // exponential number (can have fractional?)
                if (i >= s.Length || '0' > s[i] || s[i] > '9') return false;
                while (i < s.Length && ('0' <= s[i] && s[i] <= '9')) i++;
            }

            // optinal ending space
            while (i < s.Length && s[i] == ' ') i++;

            return i == s.Length;
        }

        public void Test()
        {
            Console.WriteLine(IsNumber("0") == true);
            Console.WriteLine(IsNumber(" 0.1") == true);
            Console.WriteLine(IsNumber("abc") == false);
            Console.WriteLine(IsNumber("1 a") == false);
            Console.WriteLine(IsNumber("2e10") == true);
            Console.WriteLine(IsNumber(".1") == true);
            Console.WriteLine(IsNumber("3.") == true);
            Console.WriteLine(IsNumber(".") == false);
        }
    }
}

