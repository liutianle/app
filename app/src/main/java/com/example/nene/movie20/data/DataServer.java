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
            list.add(new Video(v.getVideo_img(), v.getVideo_name(),v.getClick_num(),v.getDesc(),v.getId()));
        }
        return list;
    }
    public static List<Video> getVideoResultData(){
        List<Video> list = new ArrayList<>();
        if(VideoUtils.Video != null) {
            for (VideoInf.ResultBean v : VideoUtils.Video) {
                list.add(new Video(v.getVideo_img(), v.getVideo_name(), v.getClick_num(), v.getDesc(), v.getId()));

            }
        }
//        list.add(new Video("https://i.stack.imgur.com/wrEpx.jpg?s=32&g=1","1","22","3",1));
        return list;
    }


    public static List<MySection> getVideoData(){

        List<MySection> list = new ArrayList<>();
        list.add(new MySection(true,"最新视频",true));

        for (VideoInf.ResultBean v: VideoUtils.Video) {
            //为各个数据赋值
            list.add(new MySection(new Video(v.getVideo_img(), v.getVideo_name(),v.getClick_num(),v.getDesc(),v.getId())));
        }

        list.add(new MySection(true,"最热视频",true));
        return list;
    }

    public static List<AdminSection> getAdminData(){
        List<AdminSection> list = new ArrayList<>();
        list.add(new AdminSection(true,"学堂",false));
        list.add(new AdminSection(new Admin(R.drawable.admin_save, "收藏视频")));
        list.add(new AdminSection(new Admin(R.drawable.admin_download,"离线视频")));
        list.add(new AdminSection(new Admin(R.drawable.admin_file,"我的文档")));
        list.add(new AdminSection(true,"论坛",false));
        list.add(new AdminSection(new Admin(R.drawable.admin_question, "我的提问")));
        list.add(new AdminSection(new Admin(R.drawable.admin_answer, "我的回答")));
        list.add(new AdminSection(new Admin(R.drawable.admin_follow, "关注问题")));
        list.add(new AdminSection(new Admin(R.drawable.admin_comment, "收到评论")));
        list.add(new AdminSection(new Admin(R.drawable.admin_score, "我的积分")));

        return list;

    }

    public static List<Exam> getExamData(){
        List<Exam> list = new ArrayList<>();
        list.add(new Exam(R.drawable.exam_zonghe, "综合测试","22","50"));
        list.add(new Exam(R.drawable.exam_zhongzhi, "种植业", "12", "45"));
        list.add(new Exam(R.drawable.exam_shuichan, "水产业", "12", "45"));
        list.add(new Exam(R.drawable.user_logo, "畜牧业", "12", "45"));
        list.add(new Exam(R.drawable.exam_nongfu, "农副业", "12", "45"));
        list.add(new Exam(R.drawable.exam_nongzi, "农资业", "12", "24"));

        return list;
    }
}