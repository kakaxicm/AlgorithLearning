package com.qicode.algorithmlearning.dp;

import stack.Stack;

/**
 * Created by chenming on 2018/6/19
 * 动态规划-最长公共子序列
 * 递推公式
 * 1.C(i, j) = C(i-1, j-1)+1  x[i] = y[j]
 * 2.C(i, j) = Max(C(i-1, j),C(i, j-1)) x[i] != y[j]
 * 3.C(i, j) = 0 i=0或j=0
 */
public class LCS {
    private char[] s1, s2;//俩序列
    private int len1, len2;
    private int[][] c;//动态规划运算记录表
    private int[][] b;//记录子序列生成路径,b[i][j] = 1表示情况1，来源于左上，b[i][j]=2，来源于左,b[i][j]=3来源于上
    private final int TYPE_LEFT_TOP = 1;//左上
    private final int TYPE_LEFT = 2;//左
    private final int TYPE_TOP = 3;//上

    public LCS(String s1, String s2) {
        this.s1 = s1.toCharArray();
        this.s2 = s2.toCharArray();
        len1 = s1.length();
        len2 = s2.length();

        c = new int[len1][len2];
        b = new int[len1][len2];
    }

    /**
     * 最长公共子序列迭代
     */
    public void getLcs() {
        //初始化第一行
        int i, j;//行为s1,列为s2

        //从第二行开始迭代
        for (i = 0; i < len1; i++) {
            for (j = 0; j < len2; j++) {
                if (s1[i] == s2[j]) {
                    c[i][j] = itemWithBoundary(i - 1, j - 1) + 1;
                    b[i][j] = TYPE_LEFT_TOP;
                } else {
                    int leftItem = itemWithBoundary(i, j - 1);
                    int topItem = itemWithBoundary(i - 1, j);
                    if (leftItem >= topItem) {
                        c[i][j] = leftItem;
                        b[i][j] = TYPE_LEFT;
                    } else {
                        c[i][j] = topItem;
                        b[i][j] = TYPE_TOP;
                    }
                }
            }
        }
        dumpMatrix();
        dumpLcs1();

        dumpAllLcs();
    }

    /**
     * 超出边界返回0 否则返回c[i][j]
     */
    private int itemWithBoundary(int i, int j) {
        if (i < 0 || j < 0 || i >= len1 || j >= len2) {
            return 0;
        }
        return c[i][j];
    }

    /**
     * 借助b[][],输出Lcs
     */
    private void dumpLcs() {
        System.out.println("=======最小公共子序列=========");
        int i = len1 - 1;
        int j = len2 - 1;
        Stack<Character> stack = new Stack<>();
        while (i >= 0 && j >= 0) {
            if (b[i][j] == TYPE_LEFT_TOP) {
                stack.push(s1[i]);
                i--;
                j--;
            } else if (b[i][j] == TYPE_LEFT) {
                j--;
            } else if (b[i][j] == TYPE_TOP) {
                i--;
            }
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            Character pop = stack.pop();
            result.append(pop);
        }

        System.out.println(result.toString());
    }

    /**
     * 不借助辅助数组b[][]，c[][]中隐含了LCS信息
     * 1.c[i][j] 字符相等，则输出
     * 2.字符不相等，则判断c[i][j-1]和c[i-1][j]的大小，向较大者方向遍历
     */
    private void dumpLcs1() {
        System.out.println("=======最小公共子序列=========");
        int i = len1 - 1;
        int j = len2 - 1;
        Stack<Character> stack = new Stack<>();
        while (i >= 0 && j >= 0) {
            if (s1[i] == s2[j]) {
                stack.push(s1[i]);
                i--;
                j--;
            } else {
                int left = itemWithBoundary(i, j - 1);
                int top = itemWithBoundary(i - 1, j);
                if (left >= top) {
                    //向左遍历
                    j--;
                } else {
                    i--;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            Character pop = stack.pop();
            result.append(pop);
        }

        System.out.println(result.toString());

    }

    /**
     * 打印所有
     */
    public void dumpAllLcs() {
        System.out.println("=====所有LCS=====");
        StringBuilder sb = new StringBuilder();
        int i = len1 - 1;
        int j = len2 - 1;
        dumpLcsRes(i, j, sb);
    }

    /**
     * 递归打印
     *
     * @param sb
     */
    private void dumpLcsRes(int i, int j, StringBuilder sb) {
        sb = new StringBuilder().append(sb.toString());//新建stringbuilder，暂时存放当前结果
        while (i >= 0 && j >= 0) {
            if (s1[i] == s2[j]) {
                sb.append(s1[i]);
                i--;
                j--;
            } else {
                int left = itemWithBoundary(i, j - 1);
                int top = itemWithBoundary(i - 1, j);
                if (left > top) {
                    //向左遍历
                    j--;
                } else if (left < top) {
                    i--;
                } else {//c[i-1][j] == c[i][j-1],递归从[i-1][j]和[i][j-1]开始
                    dumpLcsRes(i - 1, j, sb);
                    dumpLcsRes(i, j - 1, sb);
                    return;
                }
            }
        }

        System.out.println(sb.toString());
    }

    /**
     * 打印迭代矩阵
     */
    private void dumpMatrix() {
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                System.out.print(c[i][j] + ", ");
            }
            System.out.println("");
        }
    }

    /**
     * 在最长公共子序列的基础上，实现最长公共字串
     * <p>
     * 递推公式
     * 1.C(i, j) = C(i-1, j-1)+1  x[i] = y[j]
     * 2.C(i, j) = 0 x[i] != y[j]
     * 3.C(i, j) = 0 i=0或j=0
     */
    public void getLcss() {
        //初始化第一行
        int i, j;//行为s1,列为s2
        int maxSubLen = 0;
        int maxX = 0, maxY = 0;//记录最长子串的结束位置
        for (i = 0; i < len1; i++) {
            for (j = 0; j < len2; j++) {
                if (s1[i] == s2[j]) {
                    c[i][j] = itemWithBoundary(i - 1, j - 1) + 1;
                } else {
                    c[i][j] = 0;
                }

                if (maxSubLen < c[i][j]) {
                    maxSubLen = c[i][j];
                    maxX = i;
                    maxY = j;
                }
            }
        }
        //打印迭代记录表C[][]
        dumpMatrix();
        System.out.println("最长公共子序列结束位置(x, y):" + maxX + ":" + maxY);
        dumpMaxLenSubString(maxX, maxY);
    }

    /**
     * 打印最长公共子串
     * 从[x,y]位置开始向左上遍历直到c[i][j]越界，或者c[i][j]==0
     *
     * @param x
     * @param y
     */
    private void dumpMaxLenSubString(int x, int y) {
        Stack<Character> stack = new Stack<>();
        int i = x;
        int j = y;
        while (i >= 0 && j >= 0 && c[i][j] > 0) {
            stack.push(s1[i]);
            i--;
            j--;
        }
        //输出最长子串
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            Character pop = stack.pop();
            result.append(pop);
        }

        System.out.println("最长公共子串为:" + result.toString());
    }
}
