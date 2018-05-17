package com.example.nene.movie20.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.nene.movie20.Interface.GetVideoInterface;
import com.example.nene.movie20.R;
import com.example.nene.movie20.activity.VideoListActivity;
import com.example.nene.movie20.activity.VideoSearchActivity;
import com.example.nene.movie20.activity.VideoWatchActivity;
import com.example.nene.movie20.adapter.VideoSectionAdapter;
import com.example.nene.movie20.adapter.VideoSectionAddAdapter;
import com.example.nene.movie20.data.DataServer;
import com.example.nene.movie20.data.MySection;
import com.example.nene.movie20.data.Video;
import com.example.nene.movie20.models.VideoInf;
import com.example.nene.movie20.utils.Constant;
import com.example.nene.movie20.utils.VideoUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by nene on 2018/4/13.
 */

public class frag_movie extends Fragment {
    private static frag_movie instance = null;
    private RecyclerView recyclerView;
    private ViewPager viewPager;
    private List<MySection> mData;
    private VideoSectionAdapter movieSectionAdapter;

    public static Fragment newInstance() {
        if (instance == null) {
            instance = new frag_movie();
        }
        return instance;
    }

    public frag_movie() {
    }

    @Override
    public void onResume() {
        super.onResume();
//        mData = DataServer.getVideoData();
        mData = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(com.example.nene.movie20.models.Constant.BaseUrl) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final GetVideoInterface getVideoInterface = retrofit.create(GetVideoInterface.class);

        //对 发送请求 进行封装
        final Call<VideoInf> call = getVideoInterface.getPage("1", "4", "-add_time");
        //TODO show dialog
        mData.clear();
        call.enqueue(new Callback<VideoInf>() {
            @Override
            public void onResponse(Call<VideoInf> call, Response<VideoInf> response) {
                List<VideoInf.ResultBean> res = new ArrayList<VideoInf.ResultBean>();
                for (VideoInf.ResultBean v: response.body().getResults()) {
                    mData.add(new MySection(new Video(v.getVideo_img(), v.getVideo_name(),v.getClick_num(),v.getDesc(),v.getId())));
                }
                movieSectionAdapter.setNewData(mData);
                // TODO close dialog
            }

            @Override
            public void onFailure(Call<VideoInf> call, Throwable t) {

            }
        });

    }

    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_movie, container, false);

        LinearLayout linearLayout = view.findViewById(R.id.movie_search);

        recyclerView = view.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        mData = DataServer.getVideoData();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VideoSearchActivity.class);
                Log.v(TAG, "search");
                startActivity(intent);
            }
        });

//        movieSectionAdapter = new VideoSectionAdapter(R.layout.content_video, R.layout.def_movie_section_head, mData);
        movieSectionAdapter = new VideoSectionAdapter(R.layout.content_video, R.layout.def_movie_section_head, new ArrayList<MySection>());

        movieSectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                MySection section = mData.get(position);
//                if (section.isMore()){
//
//                }
//                else{
                Intent intent = new Intent(getActivity(), VideoWatchActivity.class);
                intent.putExtra(Constant.VIDEO_ID, mData.get(position).t.getId());
//                    VideoUtils.getVideoInf(mData.get(position).t.getId());
//                    mData = DataServer.getVideoData();
//                    movieSectionAdapter.setNewData(mData);

                //刷新页面
                startActivity(intent);
//                }
            }
        });
        movieSectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), VideoListActivity.class);

                DataServer.getVideoData();
                if (position == 0) {
                    VideoUtils.getMoreNewVideo();
                }

                if (position == 5) {
                    VideoUtils.getMoreHotVideo();
                }
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(movieSectionAdapter);
        return view;
    }
}
