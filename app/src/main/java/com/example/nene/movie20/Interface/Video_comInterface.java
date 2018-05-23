package com.example.nene.movie20.Interface;

import com.example.nene.movie20.models.User;
import com.example.nene.movie20.models.Video_com;
import com.example.nene.movie20.models.Video_comCreate;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Video_comInterface {
    @GET("video_com/")
    Call<Video_com> getPage(@Query("page") String page,
                            @Query("page_size") String page_size);

    @GET("video_com/{id}")
    Call<Video_com> getVideoId(@Header("Authorization") String token, @Path("id") int id);

    @GET("Video_com/")
    Call<Video_comCreate> getComment(@Header("Authorization") String token,
                                     @Query("video") int video,
                                     @Query("comment") String comment);

}
