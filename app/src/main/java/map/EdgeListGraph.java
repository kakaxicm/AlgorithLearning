package map;

import android.util.Log;

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

        recursionDFS(0, isVisiteds);
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

    public int getNextNeubourVetex(int headIndex, int curIndex){
        Edge head = vertexes[headIndex].edgeHead;
        if(head == null){
            return -1;
        }

        Edge p = head;
        while(p != null){//查找curIndex的边节点
            if(p.vertextIndex == curIndex){
                break;
            }
            p = p.next;
        }
        p = p.next;
        if(p == null){
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
