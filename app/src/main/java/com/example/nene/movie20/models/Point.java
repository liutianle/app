package com.example.nene.movie20.models;

public class Point {
    private int id;
    private String  video_comment;
    private int reply_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideo_comment() {
        return video_comment;
    }

    public void setVideo_comment(String video_comment) {
        this.video_comment = video_comment;
    }

    public int getReply_type() {
        return reply_type;
    }

    public void setReply_type(int reply_type) {
        this.reply_type = reply_type;
    }
}
