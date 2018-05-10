package com.example.nene.movie20.utils;

import com.example.nene.movie20.data.Video;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetVideoInterface {
    @GET("video/")
    Call<Video> getId(@Query("page") String page,
                      @Query("page_size") String page_size); //分页
}
