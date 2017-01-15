package map;

/**
 * Created by chenming on 17/1/15.
 * 邻接表的顶点描述
 */

public class VertexForEdge {
    public int value;
    public Edge edgeHead;//指向边顶点列表
    public VertexForEdge(int value){
        this.value = value;
        edgeHead = null;
    }
}
