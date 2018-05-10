package com.example.nene.movie20.utils;

import com.example.nene.movie20.Interface.GetVideoInterface;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.VideoInf;
import com.example.nene.movie20.models.VideoUrlInf;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sam- on 2018/5/9.
 */

public class VideoUtils {
    public static String Url;
    public static List<VideoInf.ResultBean> Video;

    public static void getVideoInf(int id){
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final GetVideoInterface getVideoInterface = retrofit.create(GetVideoInterface.class);

        //对 发送请求 进行封装
        final Call<VideoUrlInf> call = getVideoInterface.getId(id);
//        final Call<VideoInf> call = getVideoInterface.getId("1", "4", "-click_num");

        //步骤6:发送网络请求(同步)
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<VideoUrlInf> response = call.execute();
                    Url = response.body().getUrl();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static void getNewVideo() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final GetVideoInterface getVideoInterface = retrofit.create(GetVideoInterface.class);

        //对 发送请求 进行封装
        final Call<VideoInf> call = getVideoInterface.getPage("1", "4", "-add_time");

        //步骤6:发送网络请求(同步)
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<VideoInf> response = call.execute();
                    Video = response.body().getResults();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    public static void getHotVideo() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final GetVideoInterface getVideoInterface = retrofit.create(GetVideoInterface.class);

        //对 发送请求 进行封装
        final Call<VideoInf> call = getVideoInterface.getPage("1", "4", "-click_num");

        //步骤6:发送网络请求(同步)
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<VideoInf> response = call.execute();
                    Video = response.body().getResults();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static void addVideoCount() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final GetVideoInterface getVideoInterface = retrofit.create(GetVideoInterface.class);

        //对 发送请求 进行封装
        final Call<VideoInf> call = getVideoInterface.getPage("1", "4", "-click_num");

        //步骤6:发送网络请求(同步)
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<VideoInf> response = call.execute();
                    Video = response.body().getResults();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
