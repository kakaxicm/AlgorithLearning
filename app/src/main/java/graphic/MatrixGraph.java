package graphic;

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
        for (int i = 0; i < vertexes.length; i++) {
            isAccessedTable[i] = false;
        }
        for (int i = 0; i < vertexes.length; i++) {
            if (!isAccessedTable[i]) {
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
        isAccessedTable[index] = true;
        System.out.println("深度优先遍历:" + vertexes[index]);//访问节点
        for (int i = 0; i < vertexes.length; i++) {
            //没有访问，且为连接顶点则递归遍历
            int temp = weightEdges[index][i];
            if (!isAccessedTable[i] && temp > 0 && temp < Integer.MAX_VALUE) {
                dfsVertexes(i, isAccessedTable);
            }
        }
    }

}
