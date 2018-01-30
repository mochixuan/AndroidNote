package com.wx.master1.bean;

import java.util.List;

/**
 * Created by wangxuan on 2018/1/26.
 */

public class WeChatBean {

    private String reason;
    private int error_code;
    private ResultBean result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    static class ResultBean {
        private int pno;
        private int ps;
        private int totalPage;
        private List<ListBean> list;

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

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }
    }

    static class ListBean {

        private String id;
        private String title;
        private String source;
        private String firstImg;
        private String mark;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getFirstImg() {
            return firstImg;
        }

        public void setFirstImg(String firstImg) {
            this.firstImg = firstImg;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
