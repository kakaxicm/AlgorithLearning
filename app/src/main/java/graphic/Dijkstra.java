package graphic;

import stack.Stack;

/**
 * Created by chenming on 2018/6/15
 * 迪科斯彻最短路径算法
 */
public class Dijkstra {
    private int[][] map;//路径图的邻接矩阵描述，顶点位置默认为索引
    private int n;//顶点个数
    public final static int INF = Integer.MAX_VALUE;

    private int[] dist;//dist[j]存放顶点j到源顶点的最短距离
    private boolean[] flg;//flg[j]=true表明该顶点的最短路径已经算出，加入集合S。
    private int[] p;//保存最短路径上的前驱顶点，p[i] = j表示顶点i的前驱顶点为j

    public Dijkstra(int[][] map) {
        if (map == null || map.length == 0) {
            throw new RuntimeException("图不能为空");
        }
        this.map = map;
        n = this.map.length;
    }

    /**
     * 最短路径迭代算法
     *
     * @param u 源顶点
     */
    public void dijkstra(int u) {
        //step1.初始化
        dist = new int[n];
        flg = new boolean[n];
        p = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = map[u][i];//初始化为u的邻接点路径数组
            flg[i] = false;
            if (dist[i] == INF) {
                p[i] = -1;
            } else {
                p[i] = u;
            }
        }
        dist[u] = 0;//u到u的距离为0
        p[u] = -1;//u的前驱初始化为-1
        flg[u] = true;//初始化S只有一个顶点u
        //step2.找最小值
        for (int i = 0; i < n; i++) {
            int t = u;//当前路径的最小的顶点
            int min = INF;
            //从V-S集合中寻找最小值
            for (int j = 0; j < n; j++) {
                if (!flg[j] && dist[j] < min) {
                    min = dist[j];
                    t = j;
                }
            }
            if (t == u) {//没找到最小值,直接返回
                return;
            }
            //将t加入集合S
            flg[t] = true;
            //根据这个最小顶点，判断V-S集合中的顶点能否借助它实现"捷径",如果能则更新dist和路径前驱
            for (int j = 0; j < n; j++) {
                //dist[t] + map[t][j] 为从经过t，到j的路径
                boolean isShortter = dist[j] > dist[t] + map[t][j];//借助t的捷径是否生效
                boolean isConnected = map[t][j] > 0 && map[t][j] < INF;//t与j是否相连
                if (!flg[j] &&  isShortter && isConnected) {
                    //更新dist
                    dist[j] = dist[t] + map[t][j];
                    //更新前驱
                    p[j] = t;

                }
            }
        }
    }

    /**
     * 打印最短距离
     */
    public void dumpDistShortestTable() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(dist[i] + ",");
        }
        System.out.println(sb.toString());
    }

    /**
     * 打印最短路径上的前驱节点
     */
    public void dumpDistShortestPath() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(p[i] + ",");
        }
        System.out.println(sb.toString());
    }

    /**
     * 打印从源节点到目标节点的路径
     * p中存的是前驱节点，因此需要引入栈来存放从源点到目标的路径
     * @param distIndex
     */
    public void dumpShortestPatnToDist(int distIndex){
        Stack<Integer> stack = new Stack<>();
        stack.push(distIndex);
        int preIndex = p[distIndex];
        while (preIndex != -1){
            stack.push(preIndex);
            preIndex = p[preIndex];
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            Integer p = stack.pop();
            if(p != null){
                sb.append(p+" ");
            }
        }

        System.out.println("到达目标:"+distIndex + " 的最短路径为:"+sb.toString()+", 路径长为:"+dist[distIndex]);
    }
}
