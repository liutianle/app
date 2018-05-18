package com.example.nene.movie20.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nene.movie20.Interface.GetVideoInterface;
import com.example.nene.movie20.Interface.Video_comInterface;
import com.example.nene.movie20.R;
import com.example.nene.movie20.adapter.VideoReviewAdapter;
import com.example.nene.movie20.data.CommentBean;
import com.example.nene.movie20.data.CommentDetailBean;
import com.example.nene.movie20.data.ReplyDetailBean;
import com.example.nene.movie20.data.Video;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.VideoUrlInf;
import com.example.nene.movie20.models.Video_com;
import com.example.nene.movie20.utils.VideoUtils;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.CommonDataSource;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nene on 2018/4/16.
 */

public class VideoWatchActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private List<CommentDetailBean> commentsList;
    private List<ReplyDetailBean> replyDetailBeans;
    private ExpandableListView expandableListView;
    private TextView bt_commont;
    private VideoReviewAdapter videoReviewAdapter;
    private static final String TAG = "VideoWatchActivity";
    private BottomSheetDialog dialog;
    private CommentBean commentBean;
    private ArrayList<String> addreview;
    private static Context context;
    private Intent intent;
    TextView content;
    TextView title;
    private String testJson = "{\n" +
            "\t\"code\": 1000,\n" +
            "\t\"message\": \"查看评论成功\",\n" +
            "\t\"data\": {\n" +
            "\t\t\"total\": 3,\n" +
            "\t\t\"list\": [{\n" +
            "\t\t\t\t\"id\": 42,\n" +
            "\t\t\t\t\"nickName\": \"程序猿\",\n" +
            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\"content\": \"时间是一切财富中最宝贵的财富。\",\n" +
            "\t\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
            "\t\t\t\t\"replyTotal\": 1,\n" +
            "\t\t\t\t\"createDate\": \"三分钟前\",\n" +
            "\t\t\t\t\"replyList\": [{\n" +
            "\t\t\t\t\t\"nickName\": \"沐風\",\n" +
            "\t\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\t\"id\": 40,\n" +
            "\t\t\t\t\t\"commentId\": \"42\",\n" +
            "\t\t\t\t\t\"content\": \"时间总是在不经意中擦肩而过,不留一点痕迹.\",\n" +
            "\t\t\t\t\t\"status\": \"01\",\n" +
            "\t\t\t\t\t\"createDate\": \"一个小时前\"\n" +
            "\t\t\t\t}]\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"id\": 41,\n" +
            "\t\t\t\t\"nickName\": \"设计狗\",\n" +
            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\"content\": \"这世界要是没有爱情，它在我们心中还会有什么意义！这就如一盏没有亮光的走马灯。\",\n" +
            "\t\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
            "\t\t\t\t\"replyTotal\": 1,\n" +
            "\t\t\t\t\"createDate\": \"一天前\",\n" +
            "\t\t\t\t\"replyList\": [{\n" +
            "\t\t\t\t\t\"nickName\": \"沐風\",\n" +
            "\t\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\t\"commentId\": \"41\",\n" +
            "\t\t\t\t\t\"content\": \"时间总是在不经意中擦肩而过,不留一点痕迹.\",\n" +
            "\t\t\t\t\t\"status\": \"01\",\n" +
            "\t\t\t\t\t\"createDate\": \"三小时前\"\n" +
            "\t\t\t\t}]\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"id\": 40,\n" +
            "\t\t\t\t\"nickName\": \"产品喵\",\n" +
            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\"content\": \"笨蛋自以为聪明，聪明人才知道自己是笨蛋。\",\n" +
            "\t\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
            "\t\t\t\t\"replyTotal\": 0,\n" +
            "\t\t\t\t\"createDate\": \"三天前\",\n" +
            "\t\t\t\t\"replyList\": []\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t}\n" +
            "}";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(VideoWatchActivity.this, Color.parseColor("#000000"));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.watch_movie);
        iniVideo();
        initView();
    }

    private void initView() {
        commentsList = generateTestData();

        expandableListView = findViewById(R.id.review_list);
        bt_commont = findViewById(R.id.input);
        bt_commont.setOnClickListener(this);
        initExpandableListView();
    }

    private List<CommentDetailBean> generateTestData() {
        Gson gson = new Gson();
        commentBean = gson.fromJson(testJson, CommentBean.class);
        List<CommentDetailBean> commentList = commentBean.getData().getList();
        return commentList;

    }

    private void initExpandableListView() {
        expandableListView.setGroupIndicator(null);
        commentsList = new ArrayList<>();
        replyDetailBeans = new ArrayList<>();
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("video_id", -1);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Video_comInterface video_comInterface = retrofit.create(Video_comInterface.class);
        String a = "JWT " + SplashActivity.Token;
        Call<Video_com> call = video_comInterface.getVideoId(a,id);
        call.enqueue(new Callback<Video_com>() {
            @Override
            public void onResponse(Call<Video_com> call, Response<Video_com> response) {
                Video_com video_com = response.body();
                for(Video_com.Results v: response.body().getResults()) {
                    for (Video_com.Results.Child_com c: v.child_com) {
                        replyDetailBeans.add(new ReplyDetailBean(c.from_uid.user_profile.nick_name,c.from_uid.user_profile.image,c.from_uid.id,c.comment_id,c.comment,c.comment,c.add_time));
                    }
                    commentsList.add(new CommentDetailBean(v.id,v.user.user_profile.nick_name,v.user.user_profile.image,v.comment,v.comment,v.child_com.size(),v.add_time,replyDetailBeans));
                    replyDetailBeans.clear();
                    expandableListView.setAdapter(videoReviewAdapter);
                }
                System.out.println(video_com);
            }

            @Override
            public void onFailure(Call<Video_com> call, Throwable t) {
                System.out.println(t);
            }
        });
        videoReviewAdapter = new VideoReviewAdapter(this, commentsList);


        for (int i = 0; i < commentsList.size(); i++) {
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                boolean isExpanded = expandableListView.isGroupExpanded(groupPosition);
                Log.e(TAG, "onGroupClick: 当前的评论id>>>" + commentsList.get(groupPosition).getId());
                expandableListView.expandGroup(groupPosition, false);
                showReplyDialog(groupPosition);
                return true;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(VideoWatchActivity.this, "点击了回复", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.input) {
            showCommentDialog();
        }

    }

    private void showCommentDialog() {
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.review_dialog, null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);

        View parent = (View) commentView.getParent();
        BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0, 0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());

        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                if (!TextUtils.isEmpty(commentContent)) {

                    //commentOnWork(commentContent);
                    dialog.dismiss();
                    CommentDetailBean detailBean = new CommentDetailBean("小明", "http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png", commentContent, "刚刚");
                    videoReviewAdapter.addTheCommentData(detailBean);
                    Toast.makeText(VideoWatchActivity.this, "评论成功", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(VideoWatchActivity.this, "评论内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence) && charSequence.length() > 2) {
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                } else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }

    private void showReplyDialog(final int position) {
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.review_dialog, null);
        final EditText commentText = commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        commentText.setHint("回复 " + commentsList.get(position).getNickName() + " 的评论:");
        dialog.setContentView(commentView);
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String replyContent = commentText.getText().toString().trim();
                if (!TextUtils.isEmpty(replyContent)) {
                    dialog.dismiss();
                    ReplyDetailBean replyDetailBean = new ReplyDetailBean("小红", replyContent);
                    videoReviewAdapter.addTheReplyData(replyDetailBean, position);
                    Toast.makeText(VideoWatchActivity.this, "回复成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(VideoWatchActivity.this, "回复内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence) && charSequence.length() > 2) {
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                } else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }


    private void iniVideo() {
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        int video_id = bundle.getInt("video_id", -1);
        getVideoInf(video_id);

    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    public void getVideoInf(int id) {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetVideoInterface getVideoInterface = retrofit.create(GetVideoInterface.class);

        //对 发送请求 进行封装
        Call<VideoUrlInf> call = getVideoInterface.getId(id);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<VideoUrlInf>() {
            @Override
            public void onResponse(Call<VideoUrlInf> call, Response<VideoUrlInf> response) {
                JZVideoPlayerStandard jzVideoPlayerStandard = findViewById(R.id.video_player);
                jzVideoPlayerStandard.setUp(response.body().getUrl(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, response.body().getVideo_name());
                title = findViewById(R.id.movie_title);
                content = findViewById(R.id.movie_content);
                title.setText(response.body().getVideo_name());
                content.setText(response.body().getDesc());

            }

            @Override
            public void onFailure(Call<VideoUrlInf> call, Throwable t) {

            }
        });
    }


}

