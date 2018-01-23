package com.ppdl.bean;

public class JhPostPara {

    /*聚合数据Post请求*/

    private int pno;
    private int ps;
    private String dtype;
    private String key;

    public JhPostPara(int pno, int ps, String dtype,String key) {
        this.pno = pno;
        this.ps = ps;
        this.dtype = dtype;
        this.key = key;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
