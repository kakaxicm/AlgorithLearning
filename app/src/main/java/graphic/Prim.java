package graphic;

/**
 * Created by chenming on 2018/6/18
 * 最小生成树算法-普利姆算法
 */
public class Prim {
    private int[][] map;
    //V-U集合中距离U集合中顶点最近的点，如closest[j] = i,
    // 表示j距离U集合最近的顶点为i,在最小树中，i为j的前驱
    private int[] closest;
    private int[] lowcost;//V-U集合中距离U最近顶点的边长(j, closest[j])
    private boolean[] s;//标记是否添加到集合U

    public Prim(int[][] map) {
        this.map = map;
        int size = map.length;
        closest = new int[size];
        lowcost = new int[size];
        s = new boolean[size];
    }


    /**
     * 构造最小生成树
     *
     * @param u0 起点顶点
     */
    public void prim(int u0) {
        int n = map.length;
        //Step1初始化
        s[u0] = true;//u0加入集合
        for (int i = 0; i < n; i++) {
            if (i != u0) {
                lowcost[i] = map[u0][i];
                closest[i] = u0;
            } else {
                lowcost[i] = 0;
                closest[i] = -1;//-1表示没有前驱
            }
        }

        //Step2 S-U中寻找距离U最小的顶点t，加入集合,更新lowcost和closest
        for (int i = 0; i < n; i++) {
            //寻找
            int min = Integer.MAX_VALUE;
            //找最小权边顶点t
            int t = u0;
            for (int j = 0; j < n; j++) {
                if (!s[j] && lowcost[j] < min) {
                    t = j;
                    min = lowcost[j];
                }
            }

            //加入集合
            if (t == u0) {//没找到最小值，跳出循环
                break;
            }
            s[t] = true;
            //更新lowcost和closest,从最小顶点t出发，如果它的邻接顶点j的边权小于lowcost[j],则更新lowcost和closest
            for (int j = 0; j < n; j++) {
                if (!s[j] && map[t][j] < lowcost[j]) {
                    lowcost[j] = map[t][j];
                    closest[j] = t;
                }
            }

        }

        //输出结果
        dumpResult();
    }

    private void dumpResult() {
        System.out.println("======最小树结构======");
        for (int i = 0; i < lowcost.length; i++) {
            System.out.println("顶点" + i + "的前驱:" + closest[i]);
        }
        System.out.println("======最小树权值======");
        for (int i = 0; i < lowcost.length; i++) {
            System.out.println("权值:" + lowcost[i]);
        }
    }
}
