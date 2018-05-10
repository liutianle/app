package com.example.nene.movie20.Interface;

import com.example.nene.movie20.models.VideoInf;
import com.example.nene.movie20.models.VideoUrlInf;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sam- on 2018/5/4.
 */

public interface GetVideoInterface {
    @GET("video/")
    Call<VideoInf> getPage(@Query("page") String page,
                         @Query("page_size") String page_size,
                         @Query("ordering") String order);

    @HTTP(method = "GET", path = "video/{id}/", hasBody = false)
    Call<VideoUrlInf> getId(@Path("id") int id);
}
