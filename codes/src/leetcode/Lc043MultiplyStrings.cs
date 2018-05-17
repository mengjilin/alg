using System;
using System.Collections.Generic;
using System.Text;

/*
 * tags: math
 * Time(nm), Space(n+m)
 */
namespace leetcode
{
    class Lc043MultiplyStrings
    {
        public string Multiply(string num1, string num2)
        {
            char[] res = new char[num1.Length + num2.Length];
            Array.Fill(res, '0');
            for (int i = num1.Length - 1; i >= 0; i--)
            {
                int carry = 0, k = res.Length - num1.Length + i;
                for (int j = num2.Length - 1; j >= 0; j--)
                {
                    int d = (num1[i] - '0') * (num2[j] - '0') + carry + (res[k] - '0');
                    res[k--] = (char)(d % 10 + '0');
                    carry = d / 10;
                }

                res[k] = (char)(res[k] + carry);
            }

            int s = 0;
            while (s < res.Length - 1 && res[s] == '0') s++;

            return new string(res, s, res.Length - s);
        }

        public void Test()
        {
            Console.WriteLine(Multiply("2", "3") == "6");
            Console.WriteLine(Multiply("123", "456") == "56088");
        }
    }
}
