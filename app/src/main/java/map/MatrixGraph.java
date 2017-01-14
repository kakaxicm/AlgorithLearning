package map;

import android.util.Log;

//import java.util.PriorityQueue;

import queue.Queue;

/**
 * Created by chenming on 17/1/14.
 * 无方向图的邻接矩阵描述
 */

public class MatrixGraph {
    public Vertex[] vertexes;//顶点数组
    public int[][] edges;//邻接矩阵描述,代表边的权重,如果i,j相连，edges[i][j]=edges[j][i]>0,否则是0

    public MatrixGraph(Vertex[] vertexes,int[][] edges){
        if(vertexes == null || edges == null || vertexes.length != edges.length){
            return;
        }
        this.vertexes = vertexes;
        this.edges = edges;
    }

    /***
     * 一个顶点的递归深度优先遍历调用
     * @param vertexIndex 顶点索引
     */
    public void dfsVertex(int vertexIndex, boolean[] isVisiteds){
        Log.e("TAG", "DFS:" + vertexes[vertexIndex].value);
        isVisiteds[vertexIndex] = true;//遍历过标记
//        printVisibles(isVisiteds);
        //向下个连接点遍历
        for(int i = 0; i < vertexes.length; i++){
            if(edges[vertexIndex][i] > 0 && !isVisiteds[i]){//有链接且未被访问过,则向下递归
                dfsVertex(i, isVisiteds);
            }
        }
    }

    private void printVisibles(boolean[] isVisiteds){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < isVisiteds.length; i++){
            sb.append(", "+ isVisiteds[i]);
        }
        Log.e("TAG",sb.toString());
    }

    public void transverseDfs(){
        boolean[] isVisiteds = new boolean[vertexes.length];

        for(int i = 0; i < vertexes.length; i++){
            isVisiteds[i] = false;
        }

        for(int i = 0; i < vertexes.length; i++){
            if(!isVisiteds[i]){
                dfsVertex(i, isVisiteds);
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
                    for(int j = 0; j < vertexes.length; j++){
                        //连接点未遍历的元素入队列
                        if(edges[headIndex][j] > 0 && !isVisiteds[j] && !unVisitVertexQueue.contains(j)){
                            unVisitVertexQueue.enquene(j);
//                            Log.e("TAG", "入队:" + j);
                        }
                    }

                    headIndex = unVisitVertexQueue.dequeue();//遍历下一个顶点
//                    Log.e("TAG", "出队:" + headIndex);
                }
            }

        }
    }
}
