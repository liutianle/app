package com.example.nene.movie20.utils;

import com.example.nene.movie20.Interface.Video_comInterface;
import com.example.nene.movie20.activity.SplashActivity;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.Video_com;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Video_comUtils {
    public static void getVideo_comUtils(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final Video_comInterface video_comInterface = retrofit.create(Video_comInterface.class);
        String a = "JWT " + SplashActivity.Token;


        final Call<Video_com.Results> call = video_comInterface.getVideoId(a,id);

        System.out.println(a);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<Video_com.Results> response = call.execute();
                    System.out.println("111111111:"+response.body().toString());
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
