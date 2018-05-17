package com.example.nene.movie20.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.nene.movie20.Interface.GetVideoInterface;
import com.example.nene.movie20.R;
import com.example.nene.movie20.adapter.VideoListAdapter;
import com.example.nene.movie20.data.DataServer;
import com.example.nene.movie20.data.MySection;
import com.example.nene.movie20.data.Video;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.VideoInf;
import com.example.nene.movie20.utils.VideoUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public VideoListAdapter videoListAdapter;
    private List<Video> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_video_list);
        setTitle("");
        //toolbar自带title

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imageView = findViewById(R.id.back);
        imageView.setOnClickListener(mGoBack);

        initView();
    }

    public View.OnClickListener mGoBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    private void initView() {
        recyclerView = findViewById(R.id.video_list_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(VideoListActivity.this, 2));

        videoListAdapter = new VideoListAdapter(R.layout.content_video, data);


        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetVideoInterface getVideoInterface = retrofit.create(GetVideoInterface.class);
        Call<VideoInf> call2 = getVideoInterface.getPage("1", "12", "-add_time");
        Call<VideoInf> call3 = getVideoInterface.getPage("1", "12", "-click_num");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String kind = bundle.getString("kind", "");
        data = new ArrayList<>();

        switch (kind) {
            case "0" :  call2.enqueue(new Callback<VideoInf>() {
                @Override
                public void onResponse(Call<VideoInf> call, Response<VideoInf> response) {
                    for (VideoInf.ResultBean v : response.body().getResults()) {
                        data.add(new Video(v.getVideo_img(), v.getVideo_name(), v.getClick_num(), v.getDesc(), v.getId()));
                    }
                    videoListAdapter.setNewData(data);
                }

                @Override
                public void onFailure(Call<VideoInf> call, Throwable t) {

                }
            });
            break;

            case "5" :call3.enqueue(new Callback<VideoInf>() {
                @Override
                public void onResponse(Call<VideoInf> call, Response<VideoInf> response) {
                    for (VideoInf.ResultBean v : response.body().getResults()) {
                        data.add(new Video(v.getVideo_img(), v.getVideo_name(), v.getClick_num(), v.getDesc(), v.getId()));
                        videoListAdapter.setNewData(data);
                    }
                }

                @Override
                public void onFailure(Call<VideoInf> call, Throwable t) {

                }
            });
            break;

            default: getKindVideo(kind); break;
        }

        recyclerView.setAdapter(videoListAdapter);
    }
    public  void getKindVideo(String kind) {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final GetVideoInterface getVideoInterface = retrofit.create(GetVideoInterface.class);

        //对 发送请求 进行封装
        final Call<VideoInf> call = getVideoInterface.getKind(kind);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<VideoInf>() {
            @Override
            public void onResponse(Call<VideoInf> call, Response<VideoInf> response) {
                for (VideoInf.ResultBean v : response.body().getResults()) {
                    data.add(new Video(v.getVideo_img(), v.getVideo_name(), v.getClick_num(), v.getDesc(), v.getId()));
                    videoListAdapter.setNewData(data);
                }
            }

            @Override
            public void onFailure(Call<VideoInf> call, Throwable t) {

            }
        });
    }

}
