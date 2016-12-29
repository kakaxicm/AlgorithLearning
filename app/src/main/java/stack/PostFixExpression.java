package stack;

import android.util.Log;

/**
 * Created by chenming on 16/12/29.
 * 栈应用:中缀表达式转后缀表达式
 * a+b*c+(d*e+f)*g ---> abc*+de*f+g*+
 */

public class PostFixExpression {
    private Stack<Character> mOperationsStack = new Stack<>();
    private StringBuilder mResult = new StringBuilder();

    private char[] OPERATIONS = {'+', '-', '*', '/'};
    private int[] OPERATION_PRIORITYS = {0, 0, 1, 1};
    private char LEFT_BRACKET = '(';
    private char RIGHT_BRACKET = ')';

    /**
     * 转换步骤
     * 1.遇到字符，直接输出到mResult
     * 2.遇到操作符
     * 1.当前操作符栈为空,直接入栈，不输出
     * 2.当前操作符堆栈不为空:
     * 1.新的操作符优先级大于栈顶操作符,入栈， 不输出
     * 2.新的操作符优先级小于等于栈顶操作符，遍历栈，将当前所有大于新操作符优先级的操作符弹栈输出!
     * 3.括号对处理:遇到闭括号，一直弹栈输出，直到左括号弹出
     *
     * @param express
     */
    public void convertMidToPostFixExpression(String express) {
        char[] chars = express.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char item = chars[i];
            if (!isOperation(item)) {//不是操作符直接输出
                mResult.append(item);
            } else {//是操作符
                if (mOperationsStack.isEmpty()) {
                    mOperationsStack.push(item);
                } else {
                    if (item == LEFT_BRACKET) {
                        mOperationsStack.push(item);//左括号直接入栈
                    } else if (item == RIGHT_BRACKET) {
                        //直接弹栈，输出,直到遇到左括号
                        while (mOperationsStack.peek() != null && mOperationsStack.peek() != LEFT_BRACKET) {
                            char charInnerBracket = mOperationsStack.pop();
                            if (charInnerBracket != LEFT_BRACKET && charInnerBracket != RIGHT_BRACKET) {
                                mResult.append(charInnerBracket);
                            }
                        }

                        //舍弃左括号
                        if (mOperationsStack.peek() != null && mOperationsStack.peek() == LEFT_BRACKET) {
                            mOperationsStack.pop();
                        }
                    } else {
                        int newCharPriority = getPriority(item);
                        //遍历堆栈，高于等于新操作符优先级的符号输出
                        Character topOperation = mOperationsStack.peek();//查看当前栈顶的操作符

                        int topCharPriority = getPriority(topOperation);
                        while (newCharPriority <= topCharPriority && !mOperationsStack.isEmpty()) {
                            if (topOperation == LEFT_BRACKET) {//左括号终止遍历,保证括号内符号优先级
                                break;
                            }
                            //将高于新符号优先级的操作符弹栈输出
                            char operation = mOperationsStack.pop();//高优先级运算符弹栈，输出
                            mResult.append(operation);
                            //下一次比较
                            topOperation = mOperationsStack.peek();
                            if (topOperation != null) {
                                topCharPriority = getPriority(topOperation);
                            }
                        }

                        mOperationsStack.push(item);//新的操作符入栈
                    }

                }
            }
        }

        /**
         * 输出剩下的操作符
         */

        while (!mOperationsStack.isEmpty()) {
            mResult.append(mOperationsStack.pop());
        }

        Log.e("TAG", "后缀表达式:" + mResult.toString());
    }

    /**
     * 操作符优先级查找
     *
     * @param op
     * @return
     */
    private int getPriority(char op) {
        if (op == LEFT_BRACKET) {
            return Integer.MAX_VALUE;
        }
        for (int i = 0; i < OPERATIONS.length; i++) {
            if (OPERATIONS[i] == op) {
                return OPERATION_PRIORITYS[i];
            }
        }
        return 0;
    }

    /**
     * 是否是操作符
     *
     * @param op
     * @return
     */
    private boolean isOperation(char op) {
        if (op == LEFT_BRACKET || op == RIGHT_BRACKET) {
            return true;
        }
        for (int i = 0; i < OPERATIONS.length; i++) {
            if (OPERATIONS[i] == op) {
                return true;
            }
        }
        return false;
    }

    /**
     * 个位数运算测试
     *
     * @param expression
     * @return
     */
    private Stack<Integer> mResultStack = new Stack<>();

    public int calulateExpression(String expression) {
        convertMidToPostFixExpression(expression);
        String postFixExpress = mResult.toString();
        char[] chars = postFixExpress.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char item = chars[i];
            if (item >= '0' && item <= '9') {
                int data = item - '0';
                mResultStack.push(data);//数据入栈
            } else {
                //操作符
                int size = mResultStack.size();
                if (size >= 2) {
                    //第一个参数先压得栈
                    int secondParam = mResultStack.pop();
                    int firstParam = mResultStack.pop();
                    switch (item) {
                        case '+':
                            mResultStack.push(secondParam + firstParam);
                            break;
                        case '*':
                            mResultStack.push(secondParam * firstParam);
                            break;
                        case '-':
                            mResultStack.push(firstParam - secondParam);
                            break;
                        case '/':
                            mResultStack.push(firstParam/secondParam);
                            break;
                    }
                }

            }
        }
        int result = mResultStack.pop();
        Log.e("TAG", "计算结果:" + result);
        return result;
    }
}
