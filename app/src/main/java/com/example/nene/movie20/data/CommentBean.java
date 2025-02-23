package com.example.nene.movie20.data;

import java.util.List;

/**
 * Created by nene on 2018/5/4.
 */

public class CommentBean {
    private int code;
    private String message;
    private CommentData data;
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setData(CommentData data) {
        this.data = data;
    }
    public CommentData getData() {
        return data;
    }

    public class CommentData {

        private int total;
        private List<CommentDetailBean> list;
        public void setTotal(int total) {
            this.total = total;
        }
        public int getTotal() {
            return total;
        }

        public void setList(List<CommentDetailBean> list) {
            this.list = list;
        }
        public List<CommentDetailBean> getList() {
            return list;
        }

    }

}
