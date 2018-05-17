package com.example.nene.movie20.utils;

import com.example.nene.movie20.Interface.GetVideoInterface;
import com.example.nene.movie20.Interface.Video_comInterface;
import com.example.nene.movie20.data.Video;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.VideoInf;
import com.example.nene.movie20.models.VideoUrlInf;
import com.example.nene.movie20.models.Video_com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by sam- on 2018/5/9.
 */

public class VideoUtils {
    public static String Url;
    public static String title;
    public static String disc;
    public static String click_num;

    public static List<VideoInf.ResultBean> Video;
    public static List<VideoInf.ResultBean> Video1;

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

        //步骤6:发送网络请求(同步)
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<VideoUrlInf> response = call.execute();
                    Url = response.body().getUrl();
                    title=response.body().getVideo_name();
                    disc=response.body().getDesc();
                    click_num = response.body().getClick_num();

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
                    Video = new ArrayList<VideoInf.ResultBean>();
                    Video.addAll(response.body().getResults());
                    System.out.println("test1");
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


    public static void getMoreNewVideo() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final GetVideoInterface getVideoInterface = retrofit.create(GetVideoInterface.class);

        //对 发送请求 进行封装
        final Call<VideoInf> call = getVideoInterface.getPage("1", "12", "-add_time");

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
                    Video1 = response.body().getResults();
                    System.out.println("hot");
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


    public static void getMoreHotVideo() {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final GetVideoInterface getVideoInterface = retrofit.create(GetVideoInterface.class);

        //对 发送请求 进行封装
        final Call<VideoInf> call = getVideoInterface.getPage("1", "12", "-click_num");

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

    public static void getSearchVideo(final String search) {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final GetVideoInterface getVideoInterface = retrofit.create(GetVideoInterface.class);

        //对 发送请求 进行封装
        final Call<VideoInf> call = getVideoInterface.getSearch(search);

        //步骤6:发送网络请求(同步)
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<VideoInf> response = call.execute();
                    Video = response.body().getResults();
                    System.out.println(response);
                    System.out.println(search);
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

    //kind有
    // 1.plant
    // 2.agri_and_sideline_industries
    // 3.agri_industry
    // 4.aquaculture
    // 5.animal
    public static void getKindVideo(String kind) {
        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final GetVideoInterface getVideoInterface = retrofit.create(GetVideoInterface.class);

        //对 发送请求 进行封装
        final Call<VideoInf> call = getVideoInterface.getKind(kind);

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
