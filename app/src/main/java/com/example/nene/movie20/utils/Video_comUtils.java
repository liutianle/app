package com.example.nene.movie20.utils;

import com.example.nene.movie20.Interface.Video_comInterface;
import com.example.nene.movie20.activity.SplashActivity;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.Video_com;
import com.example.nene.movie20.models.Video_comCreate;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Video_comUtils {
    public static void getVideo_comUtils(int id) {
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
                System.out.println(video_com);
            }

            @Override
            public void onFailure(Call<Video_com> call, Throwable t) {
                System.out.println(t);
            }
        });

        System.out.println(a);

    }

    public static void createVideo_com(int video, String comment) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Video_comInterface video_comInterface = retrofit.create(Video_comInterface.class);
        String a = "JWT " + SplashActivity.Token;
        Call<Video_comCreate> call = video_comInterface.getComment(a,video,comment);
        call.enqueue(new Callback<Video_comCreate>() {
            @Override
            public void onResponse(Call<Video_comCreate> call, Response<Video_comCreate> response) {

            }

            @Override
            public void onFailure(Call<Video_comCreate> call, Throwable t) {

            }
        });

    }
}
