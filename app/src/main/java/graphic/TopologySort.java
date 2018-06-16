package graphic;

import queue.Queue;
import stack.Stack;

/**
 * Created by chenming on 2018/6/16
 * 拓扑排序
 */
public class TopologySort {
    private class VNode {
        int value;//值
        int inDegree;//入度
    }

    private int[][] map;//邻接矩阵
    private VNode[] nodes;

    public TopologySort(int[][] map) {
        this.map = map;
        nodes = new VNode[map.length];
        for (int i = 0; i < map.length; i++) {
            //初始化入度
            VNode node = new VNode();
            node.value = i;
            node.inDegree = getIndegree(i);
            nodes[i] = node;
        }
    }

    /**
     * 从邻接矩阵中得到顶点入度
     *
     * @param index
     * @return
     */
    private int getIndegree(int index) {
        int in = 0;
        for (int i = 0; i < map.length; i++) {
            if (0 < map[i][index] && map[i][index] < Integer.MAX_VALUE) {
                in++;
            }
        }
        return in;
    }

    /**
     * 有向图的拓扑排序,，思路如下:
     * 1,类似广度优先遍历,引入队列保存入度为0的未遍历节点
     * 2.每去掉一个顶点，则去掉该顶点的边，即它所有邻接顶点的顶点入度-1，如果为0则加入队列
     * 3.循环执行步骤2，直到队列为空
     * 4.循环结束检测是否遍历完整，如果没有遍历完全，则表示有回环
     */
    public void topologySort() {
        Queue<Integer> queue = new Queue<>();//保存入度为0的索引
        int numb = map.length;
        int count = 0;//校验用
        //收集入度为0的顶点
        for (int i = 0; i < numb; i++) {
            if (nodes[i].inDegree == 0) {
                queue.enquene(i);
            }
        }

        while (!queue.isEmpty()) {
            int index = queue.dequeue();
            System.out.println("拓扑排序:" + index);
            count++;
            //遍历完"删除"顶点index的所有边,即减少它邻接顶点的入度
            for (int i = 0; i < numb; i++){
                if(map[index][i] > 0 && map[index][i] < Integer.MAX_VALUE){
                    if(--nodes[i].inDegree == 0){
                        //入度减一后，加入队列
                        queue.enquene(i);
                    }

                }
            }

        }

        if(count != numb){
            throw new RuntimeException("图中存在回环");
        }

    }
}
