package com.example.nene.movie20.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.nene.movie20.R;
import com.example.nene.movie20.data.CommentDetailBean;
import com.example.nene.movie20.data.ReplyDetailBean;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by nene on 2018/4/18.
 */

public class VideoReviewAdapter extends BaseExpandableListAdapter{
    private List<CommentDetailBean> commentDetailBeans;
    private List<ReplyDetailBean> replyDetailBeans;
    private Context context;
    private static final String TAG = "CommentExpandAdapter";

    public VideoReviewAdapter(Context context, List<CommentDetailBean> commentDetailBeans){
        this.context = context;
        this.commentDetailBeans = commentDetailBeans;
    }

    @Override
    public int getGroupCount() {
        return commentDetailBeans.size();
    }

    @Override
    public int getChildrenCount(int i) {
        if (commentDetailBeans.get(i).getReplyList() == null){
            return 0;
        }
        else{
            return commentDetailBeans.get(i).getReplyList().size() >0 ? commentDetailBeans.get(i).getReplyList().size():0;
        }
    }

    @Override
    public Object getGroup(int i) {
        return commentDetailBeans.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return commentDetailBeans.get(i).getReplyList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return getCombinedChildId(i,i1);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
    boolean isLike = false;

    @Override
    public View getGroupView(int groupPosition, boolean isExpand, View convertView, ViewGroup viewGroup) {
        final GroupHolder groupHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.review_list, viewGroup, false);
            groupHolder = new GroupHolder(convertView);
            convertView.setTag(groupHolder);
        }else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        Glide.with(context).load(R.drawable.user_other)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .into(groupHolder.logo);
        groupHolder.tv_name.setText(commentDetailBeans.get(groupPosition).getNickName());
        groupHolder.tv_time.setText(commentDetailBeans.get(groupPosition).getCreateDate());
        groupHolder.tv_content.setText(commentDetailBeans.get(groupPosition).getContent());
        groupHolder.iv_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLike){
                    isLike = false;
                    groupHolder.iv_like.setColorFilter(Color.parseColor("#aaaaaa"));
                }else {
                    isLike = true;
                    groupHolder.iv_like.setColorFilter(Color.parseColor("#FF5C5C"));
                }
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
        final ChildHolder childHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_reply_item,viewGroup, false);
            childHolder = new ChildHolder(convertView);
            convertView.setTag(childHolder);
        }
        else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        String replyUser = commentDetailBeans.get(groupPosition).getReplyList().get(childPosition).getNickName();
        if(!TextUtils.isEmpty(replyUser)){
            childHolder.tv_name.setText(replyUser + ":");
        }else {
            childHolder.tv_name.setText("无名"+":");
        }

        childHolder.tv_content.setText(commentDetailBeans.get(groupPosition).getReplyList().get(childPosition).getContent());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private class GroupHolder{
        private CircleImageView logo;
        private TextView tv_name, tv_content, tv_time;
        private ImageView iv_like;
        public GroupHolder(View view) {
            logo =  view.findViewById(R.id.comment_item_logo);
            tv_content = view.findViewById(R.id.comment_item_content);
            tv_name = view.findViewById(R.id.comment_item_userName);
            tv_time = view.findViewById(R.id.comment_item_time);
            iv_like = view.findViewById(R.id.comment_item_like);
        }

    }

    private class ChildHolder{
        private TextView tv_name, tv_content;
        public ChildHolder(View view) {
            tv_name = (TextView) view.findViewById(R.id.reply_item_user);
            tv_content = (TextView) view.findViewById(R.id.reply_item_content);
        }
    }
    BottomSheetDialog dialog;

    public void addTheCommentData(CommentDetailBean commentDetailBean){
        if(commentDetailBean !=null){
            Log.e(TAG, "addTheCommentData: >>>>该刷新评论列表了" );

            commentDetailBeans.add(commentDetailBean);
            notifyDataSetChanged();
        }else {
            throw new IllegalArgumentException("评论数据为空!");
        }
    }
    public void addTheReplyData(ReplyDetailBean replyDetailBean, int groupPosition){
        if(replyDetailBean !=null){
            Log.e(TAG, "addTheReplyData: >>>>该刷新回复列表了:"+ replyDetailBean.toString() );
            if(commentDetailBeans.get(groupPosition).getReplyList() != null ){
                Log.e(TAG, "addTheReplyData: >>>>>replyList不为空" );
                commentDetailBeans.get(groupPosition).getReplyList().add(replyDetailBean);
            }else {
                List<ReplyDetailBean> replyList = new ArrayList<>();
                replyList.add(replyDetailBean);
                commentDetailBeans.get(groupPosition).setReplyList(replyList);
            }

            notifyDataSetChanged();
        }else {
            throw new IllegalArgumentException("回复数据为空!");
        }
    }

    private void addReplyList(List<ReplyDetailBean> replyDetailBeans, int groupPosition){
        if(commentDetailBeans.get(groupPosition).getReplyList() != null ){
            commentDetailBeans.get(groupPosition).getReplyList().clear();
            commentDetailBeans.get(groupPosition).getReplyList().addAll(replyDetailBeans);
        }else {

            commentDetailBeans.get(groupPosition).setReplyList(replyDetailBeans);
        }

        notifyDataSetChanged();

    }

}

