package com.example.nene.movie20.data;

import com.example.nene.movie20.R;
import com.example.nene.movie20.models.VideoInf;
import com.example.nene.movie20.utils.GetTokenUtils;
import com.example.nene.movie20.utils.VideoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nene on 2018/4/16.
 */

public class DataServer {
    private DataServer(){
    }

    public static List<CommentDetailBean> getReviewData() {
        List<CommentDetailBean> list = new ArrayList<>();
        list.add(new CommentDetailBean("tom","http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png","good","3hours ago"));
        list.add(new CommentDetailBean("tom","http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png","good","3hours ago"));
        list.add(new CommentDetailBean("tom","http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png","good","3hours ago"));
        list.add(new CommentDetailBean("tom","http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png","good","3hours ago"));
//        list.add(new CommentDetailBean(new MainReview(R.drawable.admin,"小龙虾","小龙虾天下无敌！"), new ReplyDetailBean(R.drawable.eye,"大闸蟹","瞎说！大闸蟹才是！"),
//                new ReplyDetailBean(R.drawable.forum,"田螺","不可能比得上田螺！")));
//        list.add(new CommentDetailBean(new MainReview(R.drawable.admin,"小龙虾","小龙虾天下无敌！"), new ReplyDetailBean(R.drawable.eye,"大闸蟹","瞎说！大闸蟹才是！"),
//                new ReplyDetailBean(R.drawable.forum,"田螺","不可能比得上田螺！")));
//        list.add(new CommentDetailBean(new MainReview(R.drawable.admin,"小龙虾","小龙虾天下无敌！"), new ReplyDetailBean(R.drawable.eye,"大闸蟹","瞎说！大闸蟹才是！"),
//                new ReplyDetailBean(R.drawable.forum,"田螺","不可能比得上田螺！")));
//        list.add(new CommentDetailBean(new MainReview(R.drawable.admin,"小龙虾","小龙虾天下无敌！"), new ReplyDetailBean(R.drawable.eye,"大闸蟹","瞎说！大闸蟹才是！"),
//                new ReplyDetailBean(R.drawable.forum,"田螺","不可能比得上田螺！")));

        return list;
    }

    public static List<Video> getVideoListData(){
        List<Video> list = new ArrayList<>();
        for (VideoInf.ResultBean v:VideoUtils.Video
             ) {
            list.add(new Video(v.getVideo_img(), v.getVideo_name(),v.getClick_num(),v.getId()));
        }
        return list;
    }


    public static List<MySection> getVideoData(){

        List<MySection> list = new ArrayList<>();
        list.add(new MySection(true,"最新视频",true));
        VideoUtils.getNewVideo();
        for (VideoInf.ResultBean v: VideoUtils.Video) {
            //为各个数据赋值
            list.add(new MySection(new Video(v.getVideo_img(), v.getVideo_name(),v.getClick_num(),v.getId())));
        }

        list.add(new MySection(true,"最热视频",true));
        VideoUtils.getHotVideo();
        for (VideoInf.ResultBean v:VideoUtils.Video) {
            list.add(new MySection(new Video(v.getVideo_img(), v.getVideo_name(),v.getClick_num(),v.getId())));
        }

        return list;
    }
}