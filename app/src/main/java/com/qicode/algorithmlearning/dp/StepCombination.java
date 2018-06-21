package com.qicode.algorithmlearning.dp;

import java.util.ArrayList;

/**
 * Created by chenming on 2018/6/21
 * 走台阶问题,有n个台阶，每一步只能走1或者2台阶，求解走法个数和走法
 * 动态规划模型:
 * F(n) = F(n-1) + F(n-2)
 * F(1) = 1;
 * F(2) = 2;
 */
public class StepCombination {
    public static void stepCombination(int n) {
        int result = 0;
        //保存
        ArrayList<String> preWays = new ArrayList<>();
        ArrayList<String> prepreWays = new ArrayList<>();
        ArrayList<String> resultWays = new ArrayList<>();
        if (n == 1) {
            result = 1;
            resultWays.add("1");
            return;
        }
        if (n == 2) {
            result = 2;
            resultWays.add("11");
            resultWays.add("2");
            return;
        }

        //开始迭代
        int prepre = 1;
        int pre = 2;
        prepreWays.add("1");//F(n-2)的结果
        preWays.add("2");//F(n-1)的结果
        preWays.add("11");//到达第二台阶的所有结果
        for (int i = 3; i <= n; i++) {
            result = pre + prepre;
            prepre = pre;
            pre = result;
            //处理结果prepreWays的所有元素+2，preWays的所有元素+1，俩集合并入resultWays
            resultWays = new ArrayList<>();//new resultWays 存放新的结果
            for (int m = 0; m < prepreWays.size(); m++) {
                String s = prepreWays.get(m);
                StringBuilder sb = new StringBuilder(s);
                sb.append("2");
                s = sb.toString();
                resultWays.add(s);
            }

            for (int m = 0; m < preWays.size(); m++) {
                String s = preWays.get(m);
                StringBuilder sb = new StringBuilder(s);
                sb.append("1");
                s = sb.toString();
                resultWays.add(s);
            }
            //下一次迭代
            prepreWays = preWays;
            preWays = resultWays;
        }

        System.out.println("台阶步数:"+result);
        System.out.println("======台阶组合====");
        for(int i = 0 ; i < resultWays.size();i++){
            System.out.println(resultWays.get(i));
        }
    }
}
