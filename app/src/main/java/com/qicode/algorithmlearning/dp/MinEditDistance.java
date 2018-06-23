package com.qicode.algorithmlearning.dp;

/**
 * Created by chenming on 2018/6/23
 * 最小编辑距离
 * 迭代方程c[i][j]表示S1的字串S1[0-i]和S2[0-J]的编辑距离
 * 1.当S1[i]=S[j]时，c[i][j]=c[i-1][j-1]
 * 2.当S1[I]!=S[J]时,c[i][j]=min(c[i][j-1]+1,c[i-1][j]+1, c[i-1][j-1]),(来源左侧)取c[i][j-1]表示插入S2[j]，（来源上侧）取c[i-1][j]表示删除S1[i]
 */
public class MinEditDistance {
    public static int getMinEditDistance(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] c = new int[len1 + 1][len2 + 1];//初始化第0行为0-len2,0列为0-len1，这表示空串和它们的编辑距离,
        // 所以行列多一行,剩下的才是迭代矩阵

        for (int i = 0; i <= len2; i++) {
            c[0][i] = i;
        }
        for (int j = 0; j < len1; j++) {
            c[j][0] = j;
        }
        int dif = 0;
        //开始迭代

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dif = 0;
                } else {
                    dif = 1;
                }
                int temp = Math.min(c[i - 1][j], c[i][j - 1]) + 1;
                c[i][j] = Math.min(temp, c[i - 1][j - 1] + dif);
            }

        }

        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                System.out.print(c[i][j] + ", ");
            }
            System.out.println("");
        }
        System.out.println("=====编辑过程======");
        int startX = len1;
        int startY = len2;
        while (startX >= 1 && startY >= 1) {
            if (s1.charAt(startX - 1) == s2.charAt(startY - 1)) {//字符相等啥也不做,左上方移动
                startX--;
                startY--;
            } else {
                int upVal = c[startX - 1][startY] + 1;
                int leftVal = c[startX][startY - 1] + 1;
                int upLeft = c[startX - 1][startY - 1] + 1;
                if (c[startX][startY] == upVal) {//来源上侧,取c[i-1][j]表示删除S1[i]
                    System.out.println("删除" + s1.charAt(startX - 1));
                    startX--;
                } else if (c[startX][startY] == leftVal) {//(来源左侧)取c[i][j-1]表示插入S2[j]
                    System.out.println("插入" + s2.charAt(startY - 1));
                    startY--;
                } else if (c[startX][startY] == upLeft) {//来源左上表示替换s1[i]为s2[j]
                    System.out.println("替换" + s1.charAt(startX - 1) + "为" + s2.charAt(startY - 1));
                    startX--;
                    startY--;
                }
            }
        }
        return c[len1][len2];
    }

    /**
     * 输出编辑过程
     *
     * @param c    矩阵
     * @param len1 行数
     * @param len2 列数
     */
    private static void dumpEditProcess(int[][] c, int len1, int len2) {

    }
}
