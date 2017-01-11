package tree;

/**
 * Created by chenming on 17/1/11.
 */

public class HuffmanModel implements Comparable<HuffmanModel>{
    public Integer weight;//权重
    public int data;//data
    public String code = "";//路径编码
    public HuffmanModel(int data){
        this.weight = this.data = data;//简化处理
    }
    @Override
    public int compareTo(HuffmanModel o) {
        return this.weight.compareTo(o.weight);
    }
}
