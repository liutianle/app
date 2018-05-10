package com.example.nene.movie20.models;

import java.util.List;

/**
 * Created by sam- on 2018/5/3.
 */

public class VideoInf {
    private String count;
    private String next;
    private String previous;
    private List<ResultBean> results;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<ResultBean> getResults() {
        return results;
    }

    public void setResults(List<ResultBean> results) {
        this.results = results;
    }

    public static class ResultBean{
        public int id;
        public String video_name;
        public String desc;
        public String video_img;
        public String click_num;
        public String video_kind;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVideo_name() {
            return video_name;
        }

        public void setVideo_name(String video_name) {
            this.video_name = video_name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getVideo_img() {
            return video_img;
        }

        public void setVideo_img(String video_img) {
            this.video_img = video_img;
        }

        public String getClick_num() {
            return click_num;
        }

        public void setClick_num(String click_num) {
            this.click_num = click_num;
        }

        public String getVideo_kind() {
            return video_kind;
        }

        public void setVideo_kind(String video_kind) {
            this.video_kind = video_kind;
        }
    }

}