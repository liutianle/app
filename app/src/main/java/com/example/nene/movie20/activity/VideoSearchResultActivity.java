package com.example.nene.movie20.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.nene.movie20.Interface.GetVideoInterface;
import com.example.nene.movie20.R;
import com.example.nene.movie20.adapter.VideoListAdapter;
import com.example.nene.movie20.adapter.VideoResultAdapter;
import com.example.nene.movie20.data.DataServer;
import com.example.nene.movie20.data.Video;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.VideoInf;
import com.example.nene.movie20.utils.VideoUtils;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoSearchResultActivity extends AppCompatActivity {

    private Intent intent;
    private RecyclerView recyclerView;
    private VideoResultAdapter videoResultAdapter;
    private TextView setText;
    private List<Video> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_search_result);

        initView();
        initText();
    }

    private void initText() {
        setText = findViewById(R.id.search_bar_result);
        intent = getIntent();
        String result = intent.getStringExtra("search_result");
        setText.setText(result);
        getSearchVideo(result);


    }

    private void initView() {
        recyclerView = findViewById(R.id.video_search_result);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        data = new ArrayList<>();

        videoResultAdapter = new VideoResultAdapter(R.layout.content_video_search_result, data);
        recyclerView.setAdapter(videoResultAdapter);
    }


    public void getSearchVideo(String search) {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetVideoInterface getVideoInterface = retrofit.create(GetVideoInterface.class);

        //对 发送请求 进行封装
        Call<VideoInf> call = getVideoInterface.getSearch(search);

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<VideoInf>() {
            @Override
            public void onResponse(Call<VideoInf> call, Response<VideoInf> response) {
                for (VideoInf.ResultBean v: response.body().getResults()
                        ) {
                    data.add(new Video(v.getVideo_img(), v.getVideo_name(),v.getClick_num(),v.getDesc(),v.getId()));
                }
                videoResultAdapter.setNewData(data);
            }

            @Override
            public void onFailure(Call<VideoInf> call, Throwable t) {

            }
        });
    }

}
