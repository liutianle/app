package com.example.nene.movie20.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.nene.movie20.Interface.GetVideoInterface;
import com.example.nene.movie20.R;
import com.example.nene.movie20.activity.VideoListActivity;
import com.example.nene.movie20.activity.VideoSearchActivity;
import com.example.nene.movie20.activity.VideoWatchActivity;
import com.example.nene.movie20.adapter.VideoSectionAdapter;
import com.example.nene.movie20.data.MySection;
import com.example.nene.movie20.data.Video;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.VideoInf;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nene on 2018/4/13.
 */

public class frag_movie extends Fragment {
    private static frag_movie instance = null;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private ViewPager viewPager;
    private List<MySection> mData;
    private List<MySection> mData1;
    private VideoSectionAdapter movieSectionAdapter;
    private VideoSectionAdapter movieSectionAdapter2;



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
        mData = new ArrayList<>();
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetVideoInterface getVideoInterface = retrofit.create(GetVideoInterface.class);

        //对 发送请求 进行封装
        Call<VideoInf> call = getVideoInterface.getPage("1", "4", "-add_time");
        Call<VideoInf> call1 = getVideoInterface.getPage("1", "4", "-click_num");

        call.enqueue(new Callback<VideoInf>() {
            @Override
            public void onResponse(Call<VideoInf> call, Response<VideoInf> response) {
                mData.add(new MySection(true,"最新视频",true));
                for (VideoInf.ResultBean v : response.body().getResults()) {
                    mData.add(new MySection(new Video(v.getVideo_img(), v.getVideo_name(), v.getClick_num(), v.getDesc(), v.getId())));
            }
                movieSectionAdapter.setNewData(mData);
            }

            @Override
            public void onFailure(Call<VideoInf> call, Throwable t) {

            }
        });

        call1.enqueue(new Callback<VideoInf>() {
            @Override
            public void onResponse(Call<VideoInf> call, Response<VideoInf> response) {
                mData1=new ArrayList<>();
                mData1.add(new MySection(true,"最热视频",true));
                for (VideoInf.ResultBean v : response.body().getResults()) {
                    mData1.add(new MySection(new Video(v.getVideo_img(), v.getVideo_name(), v.getClick_num(), v.getDesc(), v.getId())));
                }
                movieSectionAdapter2.setNewData(mData1);
            }

            @Override
            public void onFailure(Call<VideoInf> call, Throwable t) {

            }
        });

    }

    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_movie, container, false);

        LinearLayout linearLayout = view.findViewById(R.id.movie_search);

//        MyGridLayoutManager recyclerViewLayoutManger = new MyGridLayoutManager(getContext(), 2);
//        recyclerViewLayoutManger.setScrollEnabled(false);
//        MyGridLayoutManager recyclerViewLayoutManger2 = new MyGridLayoutManager(getContext(), 2);
//        recyclerViewLayoutManger.setScrollEnabled(false);
//        recyclerViewLayoutManger2.setScrollEnabled(false);
        recyclerView = view.findViewById(R.id.rv_list);
        recyclerView2 = view.findViewById(R.id.rv_list2);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView2.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView2.setNestedScrollingEnabled(false);
        movieSectionAdapter = new VideoSectionAdapter(R.layout.content_video, R.layout.def_movie_section_head, new ArrayList<MySection>());
        movieSectionAdapter2 = new VideoSectionAdapter(R.layout.content_video, R.layout.def_movie_section_head, new ArrayList<MySection>());
        mData = new ArrayList<>();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VideoSearchActivity.class);
                startActivity(intent);
            }
        });


        movieSectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                    Intent intent = new Intent(getActivity(), VideoWatchActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("video_id", mData.get(position).t.getId());
                    intent.putExtras(bundle);
                    //刷新页面d
                    startActivity(intent);

            }
        });
        movieSectionAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                MySection section = mData1.get(position);
                if (section.isMore()) {

                } else {
                    Intent intent = new Intent(getActivity(), VideoWatchActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("video_id", mData1.get(position).t.getId());
                    intent.putExtras(bundle);
                    //刷新页面d
                    startActivity(intent);
                }
            }
        });
        movieSectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), VideoListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("kind", String.valueOf(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        movieSectionAdapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), VideoListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("kind", String.valueOf(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(movieSectionAdapter);
        recyclerView2.setAdapter(movieSectionAdapter2);
        return view;
    }
}
