package com.qicode.algorithmlearning.dp;

/**
 * Created by chenming on 2018/6/22
 * 最长回文字串问题
 * 迭代方程:c[j][i] 记录从索引j到i的字串是否为回文
 * <p>
 * 1.当i=j时表示只有一个元素,c[j][i]==true
 * 2.当i-j=1时:字串长度为2，c[j][i]为s[i]和s[j]是否相等，即c[j][i]=(s[i]==s[j])
 * 3.当i-j > 1时:
 * s[i] != s[j],则c[j][i]=false
 * s[i] = s[j],则判断字串[j+1, i-1]是否为回文，即c[j+1][i-1]=(s[i]==s[j])&&c[j+1][i+1]
 */
public class Palindrome {
    public static String longestPalindromeStr(String s) {
        int n = s.length();
        boolean[][] c = new boolean[n][n];
        int maxlen = 1;//最长回文子串长度
        int startIndex = 0;//最长回文起点
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {//从j到i迭代
                if (i - j < 2) {
                    c[j][i] = (s.charAt(i) == s.charAt(j));
                } else if (i - j > 1) {
                    c[j][i] = (s.charAt(i) == s.charAt(j)) && c[j + 1][i - 1];
                }

                if (c[j][i] && maxlen < i - j + 1) {
                    maxlen = i - j + 1;
                    startIndex = j;
                }
            }
        }

        String result = s.substring(startIndex, startIndex+maxlen);
        System.out.println("====最长回文====");
        System.out.println(result);
        return result;
    }
}
