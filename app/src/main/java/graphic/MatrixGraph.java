package graphic;


import queue.Queue;

/**
 * Created by chenming on 2018/6/15
 */
public class MatrixGraph {
    private int[][] weightEdges;//带权重边二维数组，weightEdges[i][j]表示节点i到j的权重
    private int[] vertexes;//顶点数组
    public static int NO_WEIGHT_VALUE = Integer.MAX_VALUE;

    public MatrixGraph(int[] vertexes, int[][] edges) {
        if (vertexes == null || edges == null || vertexes.length != edges.length) {
            return;
        }
        this.vertexes = vertexes;
        this.weightEdges = edges;
    }

    /**
     * 深度优先遍历
     */
    public void transverseDfs() {
        boolean[] isAccessedTable = new boolean[vertexes.length];
        //初始化访问标记数组
        for (int i = 0; i < vertexes.length; i++) {
            isAccessedTable[i] = false;
        }

        for (int i = 0; i < vertexes.length; i++) {
            if (!isAccessedTable[i]) {
                //递归深度优先遍历
                dfsVertexes(i, isAccessedTable);
            }
        }
    }


    /**
     * 递归深度遍历顶点
     *
     * @param index           顶点索引
     * @param isAccessedTable
     */
    private void dfsVertexes(int index, boolean[] isAccessedTable) {
        isAccessedTable[index] = true;//访问节点
        System.out.println("深度优先遍历:" + vertexes[index]);//访问节点
        for (int i = 0; i < vertexes.length; i++) {
            //没有访问，且为连接顶点则递归遍历
            int temp = weightEdges[index][i];
            if (!isAccessedTable[i] && temp > 0 && temp < Integer.MAX_VALUE) {
                dfsVertexes(i, isAccessedTable);
            }
        }
    }

    /**
     * 广度遍历和二次树的层序遍历相似,引入队列，存放当前未访问的连接节点
     */
    public void transverseBfs() {
        boolean[] isAccessedTable = new boolean[vertexes.length];
        for (int i = 0; i < vertexes.length; i++) {
            isAccessedTable[i] = false;
        }
        Integer headIndex;
        Queue<Integer> queue = new Queue<>();//也可以用LinkList代替
        for (int i = 0; i < vertexes.length; i++) {//各顶点作为入口
            if (!isAccessedTable[i]) {
                headIndex = i;//层序遍历起点
                while (headIndex != null) {//一次层序遍历,直到遍历完最后一层，队列为空
                    isAccessedTable[headIndex] = true;//遍历当前节点
                    System.out.println("广度优先遍历:" + vertexes[headIndex]);
                    //查找未访问的连接点入队
                    for (int j = 0; j < vertexes.length; j++) {
                        int tmp = weightEdges[headIndex][j];
                        if (!isAccessedTable[j] && tmp > 0 && tmp < Integer.MAX_VALUE && !queue.contains(j)) {
                            //未访问的连接点j入队
                            queue.enquene(j);
                        }
                    }
                    headIndex = queue.dequeue();//取未访问的节点
                }
            }
        }
    }

}
