package com.example.nene.movie20.Interface;

import com.example.nene.movie20.models.Video_com;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Video_comInterface {
    @GET("video_com/")
    Call<Video_com> getPage(@Query("page") String page,
                            @Query("page_size") String page_size);

}
