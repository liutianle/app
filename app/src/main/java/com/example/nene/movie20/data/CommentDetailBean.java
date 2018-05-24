package com.example.nene.movie20.data;

import java.util.List;

/**
 * Created by nene on 2018/4/20.
 */

public class CommentDetailBean {
    private int id;
    private String nickName;
    private String userLogo;
    private String content;
    private int userId;
    private int replyTotal;
    private String createDate;
    private List<ReplyDetailBean> replyList;

    public CommentDetailBean(int id, String nickName, String userLogo, String content, int userId, int replyTotal, String createDate, List<ReplyDetailBean> replyList) {
        this.id = id;
        this.nickName = nickName;
        this.userLogo = userLogo;
        this.content = content;
        this.userId = userId;
        this.replyTotal = replyTotal;
        this.createDate = createDate;
        this.replyList = replyList;
    }

    public CommentDetailBean(String nickName, String userLogo, String content, String createDate) {
        this.nickName = nickName;
        this.userLogo = userLogo;
        this.content = content;
        this.createDate = createDate;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getNickName() {
        return nickName;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }
    public String getUserLogo() {
        return userLogo;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setUserId(int imgId) {
        this.userId = userId;
    }
    public int getUserId() {
        return userId;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setReplyTotal(int replyTotal) {
        this.replyTotal = replyTotal;
    }
    public int getReplyTotal() {
        return replyTotal;
    }

    public void setReplyList(List<ReplyDetailBean> replyList) {
        this.replyList = replyList;
    }
    public List<ReplyDetailBean> getReplyList() {
        return replyList;
    }
}
