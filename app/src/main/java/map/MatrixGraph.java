package map;

import android.util.Log;

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

//    private void printVisibles(boolean[] isVisiteds){
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0; i < isVisiteds.length; i++){
//            sb.append(", "+ isVisiteds[i]);
//        }
//        Log.e("TAG",sb.toString());
//    }

    public void tranverseDfs(){
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
}
