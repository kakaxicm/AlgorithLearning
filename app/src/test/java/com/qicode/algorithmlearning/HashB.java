package com.qicode.algorithmlearning;

/**
 * Created by chenming on 2018/6/8
 */
public class HashB {
    String s;

    public HashB(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public int hashCode() {
        return s.hashCode();
    }
}
