package stack;

import android.util.Log;

import List.Node;

/**
 * Created by chenming on 16/12/27.
 */

public class HanoiTower {

    public void moveTop(Stack<Integer> source, Stack<Integer> des) {
        Integer top = source.pop();
        if (top != null) {
            des.push(top);
        }
    }

    /**
     * 汉诺塔递归
     * 1.n=1,最后一个x--->z
     * 2.n>1:步骤
     *   n-1 x->y,z为辅助
     *   第n个x->z
     *   y上的n-1个->z,x为辅助
     *
     * @param n 塔个数
     * @param x source
     * @param y middle
     * @param z des
     */
    public void hanoi(int n, Stack<Integer> x, Stack<Integer> y, Stack<Integer> z) {
        if (n == 1) {
            moveTop(x, z);
            return;
        }

        hanoi(n - 1, x, z, y);
        printStack(x);
        printStack(y);
        printStack(z);
        Log.e("TAG", "=========");
        moveTop(x, z);
        printStack(x);
        printStack(y);
        printStack(z);
        Log.e("TAG", "=========");
        hanoi(n - 1, y, x, z);
        printStack(x);
        printStack(y);
        printStack(z);
        Log.e("TAG", "=========");
    }

    /**
     * 打印栈
     * @param stack
     */
    public void printStack(Stack<Integer> stack){
        Node<Integer> node = stack.mTop;
        StringBuilder sb = new StringBuilder(stack.ID+"=");
        while (node != null){
            sb.append(node.mData.toString());
            sb.append(",");
            node = node.next;
        }
        Log.e("TAG", sb.toString());
    }
}
