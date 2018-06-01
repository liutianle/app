package com.example.nene.movie20.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Short2;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nene.movie20.Interface.GetVideoInterface;
import com.example.nene.movie20.Interface.UserFavVideoInterface;
import com.example.nene.movie20.Interface.UserInfInterface;
import com.example.nene.movie20.Interface.Video_comInterface;
import com.example.nene.movie20.R;
import com.example.nene.movie20.adapter.VideoReviewAdapter;
import com.example.nene.movie20.data.CommentBean;
import com.example.nene.movie20.data.CommentDetailBean;
import com.example.nene.movie20.data.ReplyDetailBean;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.User;
import com.example.nene.movie20.models.VideoUrlInf;
import com.example.nene.movie20.models.Video_Reply;
import com.example.nene.movie20.models.Video_com;
import com.example.nene.movie20.models.Video_comCreate;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nene on 2018/4/16.
 */

public class VideoWatchActivity extends AppCompatActivity implements View.OnClickListener {
    private final int GET_COMMENTLIST = 1;
    private final int GET_FAVVIDEOLIST = 1;
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
    private Intent intent;
    private String commentContent;
    private SharedPreferences sharedPreferences;
    private String replyContent;
    private ReplyDetailBean replyDetailBean;
    private Handler handler;
    private Handler handler2;
    private TextView content;
    private TextView title;
    boolean isSave = false;
    private ImageView save;
    private int video_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(VideoWatchActivity.this, Color.parseColor("#000000"));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.watch_movie);
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
        SharedPreferences sharedPreferences = getSharedPreferences("Token", 0);
        String a = "JWT " + sharedPreferences.getString("Token", "");
        Call<Video_com> call = video_comInterface.getVideoId(a, id);
        call.enqueue(new Callback<Video_com>() {
            @Override
            public void onResponse(Call<Video_com> call, Response<Video_com> response) {
                for (Video_com.Results v : response.body().getResults()) {
                    replyDetailBeans = new ArrayList<>();
                    for (Video_com.Results.Child_com c : v.child_com) {
                        replyDetailBeans.add(new ReplyDetailBean(c.from_uid.user_profile.nick_name, c.from_uid.user_profile.image, c.from_uid.id, c.comment_id, c.comment, c.comment, c.add_time));
                    }
                    commentsList.add(new CommentDetailBean(v.id, v.user.user_profile.nick_name, v.user.user_profile.image, v.comment, v.user.id, v.child_com.size(), v.add_time, v.is_love, v.point_love_nums, replyDetailBeans));
                }
                Collections.reverse(commentsList);
                Message msg = new Message();
                msg.what = GET_COMMENTLIST;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<Video_com> call, Throwable t) {
            }
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        iniVideo();
                        initView();
                        initSave();
                        break;
                }
                return true;
            }
        });


    }

    private void initSave() {
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        video_id = bundle.getInt("video_id", -1);
        getFavList(video_id);
        save = findViewById(R.id.video_like);
        handler2 = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case GET_FAVVIDEOLIST:
                        save.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (!isSave) {
                                    isSave = true;
                                    addFavVideo(video_id);
                                    save.setColorFilter(Color.parseColor("#FF5C5C"));
                                } else {
                                    save.setColorFilter(Color.parseColor("#aaaaaa"));
                                    isSave = false;
                                    deleteFavVideo(video_id);
                                }
                            }
                        });
                        ;
                        break;
                }
                return false;
            }
        });
    }


    private void initView() {
        expandableListView = findViewById(R.id.review_list);
        bt_commont = findViewById(R.id.input);
        bt_commont.setOnClickListener(this);
        initExpandableListView();
    }


    private void initExpandableListView() {
        expandableListView.setGroupIndicator(null);
        videoReviewAdapter = new VideoReviewAdapter(this, commentsList);
        expandableListView.setAdapter(videoReviewAdapter);


        for (int i = 0; i < commentsList.size(); i++) {
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                boolean isExpanded = expandableListView.isGroupExpanded(groupPosition);
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
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0, 0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());

        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentContent = commentText.getText().toString().trim();
                if (!TextUtils.isEmpty(commentContent)) {

                    //commentOnWork(commentContent);
                    dialog.dismiss();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constant.BaseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    sharedPreferences = getSharedPreferences("Token", Context.MODE_PRIVATE);
                    UserInfInterface userInfInterface = retrofit.create(UserInfInterface.class);
                    Call<User> call = userInfInterface.getinformation("JWT " + sharedPreferences.getString("Token", ""), "1");
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            CommentDetailBean detailBean = new CommentDetailBean(response.body().getUser_profile().nick_name, response.body().getUser_profile().image, commentContent, getTime());
                            videoReviewAdapter.addTheCommentData(detailBean);
                            Toast.makeText(VideoWatchActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                            addVideo_com(commentText.getText().toString());
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
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
                replyContent = commentText.getText().toString().trim();
                if (!TextUtils.isEmpty(replyContent)) {
                    dialog.dismiss();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constant.BaseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    sharedPreferences = getSharedPreferences("Token", Context.MODE_PRIVATE);
                    UserInfInterface userInfInterface = retrofit.create(UserInfInterface.class);
                    Call<User> call = userInfInterface.getinformation("JWT " + sharedPreferences.getString("Token", ""), "1");
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            replyDetailBean = new ReplyDetailBean(response.body().getUser_profile().getNick_name(), replyContent);
                            videoReviewAdapter.addTheReplyData(replyDetailBean, position);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });

                    Toast.makeText(VideoWatchActivity.this, "回复成功", Toast.LENGTH_SHORT).show();
                    addReply(commentsList.get(position).getId(), commentsList.get(position).getUserId(), replyContent);
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

    public String getTime() {
        Calendar calendar = Calendar.getInstance();
//获取系统的日期
//年
        int year = calendar.get(Calendar.YEAR);
//月
        int month = calendar.get(Calendar.MONTH) + 1;
//日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
//获取系统时间
//小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//分钟
        int minute = calendar.get(Calendar.MINUTE);
//秒
        int second = calendar.get(Calendar.SECOND);

        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
    }

    public void addVideo_com(String comment) {

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        int video_id = bundle.getInt("video_id", -1);
        sharedPreferences = getSharedPreferences("Token", 0);
        String a = "JWT " + sharedPreferences.getString("Token", "");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Video_comInterface video_comInterface = retrofit.create(Video_comInterface.class);
        Call<Video_comCreate> call = video_comInterface.getComment(a, video_id, comment);
        call.enqueue(new Callback<Video_comCreate>() {
            @Override
            public void onResponse(Call<Video_comCreate> call, Response<Video_comCreate> response) {

            }

            @Override
            public void onFailure(Call<Video_comCreate> call, Throwable t) {

            }
        });
    }

    public void addReply(int id, int uid, String comment) {


        sharedPreferences = getSharedPreferences("Token", 0);
        String a = "JWT " + sharedPreferences.getString("Token", "");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Video_comInterface video_comInterface = retrofit.create(Video_comInterface.class);
        Call<Video_Reply> call = video_comInterface.getReply(a, id, id, uid, 1, comment);
        call.enqueue(new Callback<Video_Reply>() {
            @Override
            public void onResponse(Call<Video_Reply> call, Response<Video_Reply> response) {

            }

            @Override
            public void onFailure(Call<Video_Reply> call, Throwable t) {

            }
        });
    }

    public void addFavVideo(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserFavVideoInterface userFavVideoInterface = retrofit.create(UserFavVideoInterface.class);

        sharedPreferences = getSharedPreferences("Token", 0);
        Call<String> call = userFavVideoInterface.getVideoId("JWT " + sharedPreferences.getString("Token", "-1"), id);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void deleteFavVideo(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserFavVideoInterface userFavVideoInterface = retrofit.create(UserFavVideoInterface.class);
        sharedPreferences = getSharedPreferences("Token", 0);
        Call<String> call = userFavVideoInterface.getDelVideoid("JWT " + sharedPreferences.getString("Token", ""), id);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void getFavList(final int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserFavVideoInterface userFavVideoInterface = retrofit.create(UserFavVideoInterface.class);
        sharedPreferences = getSharedPreferences("Token", 0);
        Call<List<Map<String, VideoUrlInf>>> call = userFavVideoInterface.getFavVideoList("JWT " + sharedPreferences.getString("Token", ""));
        call.enqueue(new Callback<List<Map<String, VideoUrlInf>>>() {
            @Override
            public void onResponse(Call<List<Map<String, VideoUrlInf>>> call, Response<List<Map<String, VideoUrlInf>>> response) {
                for (Map<String, VideoUrlInf> map: response.body()) {
                    if(map.get("video").getId() == id){
                        save.setColorFilter(Color.parseColor("#FF5C5C"));
                        isSave = true;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, VideoUrlInf>>> call, Throwable t) {

            }
        });
    }
}

