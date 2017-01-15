package map;

import android.util.Log;

import queue.Queue;
import stack.Stack;

/**
 * Created by chenming on 17/1/15.
 */

public class EdgeListGraph {
    public VertexForEdge[] vertexes;//顶点数组，每个顶点包含邻接点链表

    public void recursionDFS() {
        boolean[] isVisiteds = new boolean[vertexes.length];
        for (int i = 0; i < vertexes.length; i++) {
            isVisiteds[i] = false;
        }
        for(int i= 0; i < vertexes.length; i++){
            if(!isVisiteds[i]){
                recursionDFS(i, isVisiteds);
            }
        }

    }

    /**
     * 递归深度遍历
     *
     * @param vertextIndex 节点索引
     * @param visited
     */
    public void recursionDFS(int vertextIndex, boolean[] visited) {
        visited[vertextIndex] = true;
        Log.e("TAG", "DFS:" + vertexes[vertextIndex].value);
        //找到与当前顶点相连的第一个顶点
        int nextVertexIndex = getFirstNeibourVertex(vertextIndex);
        while (nextVertexIndex != -1) {
            if (!visited[nextVertexIndex]) {//如果没被访问过,则递归访问
                recursionDFS(nextVertexIndex, visited);
            }
            nextVertexIndex = getNextNeubourVetex(vertextIndex, nextVertexIndex);//vertextIndex的邻接点list遍历
        }

    }


    /**
     * 迭代深度遍历
     */
    public void iterationDFS() {
        boolean[] isVisiteds = new boolean[vertexes.length];
        for (int i = 0; i < vertexes.length; i++) {
            isVisiteds[i] = false;
        }

        for(int i= 0; i < vertexes.length; i++){
            if(!isVisiteds[i]){
                iterationDFS(i, isVisiteds);
            }
        }
    }

    /**
     * 迭代深度遍历
     * @param vertextIndex
     * @param visited
     */
    public void iterationDFS(int vertextIndex, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();

        visited[vertextIndex] = true;
        Log.e("TAG", "DFS:" + vertexes[vertextIndex].value);
        stack.push(vertextIndex);//访问过的顶点入栈
        while (!stack.isEmpty()) {
            int lastVistedIndex = stack.pop();

            //访问第一个未访问节点,。如果找到 则访问后，退出小循环，下次从这个节点继续在其邻接表中遍历
            int nextVertexIndex = getFirstNeibourVertex(lastVistedIndex);
            while (nextVertexIndex != -1) {
                if (!visited[nextVertexIndex]) {
                    //访问
                    visited[nextVertexIndex] = true;
                    Log.e("TAG", "DFS:" + vertexes[nextVertexIndex].value);
                    stack.push(nextVertexIndex);
                    break;
                }
                nextVertexIndex = getNextNeubourVetex(lastVistedIndex, nextVertexIndex);//lastVistedIndex的邻接点list遍历
            }

        }
    }

    /**
     * 广度优先遍历,类似树的层序遍历,引入队列，保存每一层未遍历的顶点
     */
    public void transverBfs(){
        boolean[] isVisiteds = new boolean[vertexes.length];
        Queue<Integer> unVisitVertexQueue = new Queue<>();
        for(int i = 0; i < vertexes.length; i++){
            isVisiteds[i] = false;
        }

        for(int i = 0; i < vertexes.length; i++){
            if(!isVisiteds[i]){
                Integer headIndex = i;//循环初始条件
                while(headIndex != null){
                    //遍历当前节点
                    Log.e("TAG", "BFS:" + vertexes[headIndex].value);
                    isVisiteds[headIndex] = true;

                    //将下一层节点未遍历顶点入队
                    int nextVertexIndex = getFirstNeibourVertex(headIndex);
                    while (nextVertexIndex != -1) {

                        if (!isVisiteds[nextVertexIndex] && !unVisitVertexQueue.contains(nextVertexIndex)) {
                            unVisitVertexQueue.enquene(nextVertexIndex);
                        }
                        nextVertexIndex = getNextNeubourVetex(headIndex, nextVertexIndex);//lastVistedIndex的邻接点list遍历
                    }

                    headIndex = unVisitVertexQueue.dequeue();//遍历下一个顶点
//                    Log.e("TAG", "出队:" + headIndex);
                }
            }

        }
    }

    public int getNextNeubourVetex(int headIndex, int curIndex) {
        Edge head = vertexes[headIndex].edgeHead;
        if (head == null) {
            return -1;
        }

        Edge p = head;
        while (p != null) {//查找curIndex的边节点
            if (p.vertextIndex == curIndex) {
                break;
            }
            p = p.next;
        }

        if(p != null){
            p = p.next;//curIndex下一个节点
        }

        if (p == null) {
            return -1;
        }
        return p.vertextIndex;
    }

    public int getFirstNeibourVertex(int vertextIndex) {
        Edge edgeHead = vertexes[vertextIndex].edgeHead;
        if (edgeHead != null) {
            return edgeHead.vertextIndex;
        }
        return -1;
    }
}
