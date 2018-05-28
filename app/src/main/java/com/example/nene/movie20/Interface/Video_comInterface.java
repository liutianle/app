package com.example.nene.movie20.Interface;

import com.example.nene.movie20.models.User;
import com.example.nene.movie20.models.Video_Reply;
import com.example.nene.movie20.models.Video_com;
import com.example.nene.movie20.models.Video_comCreate;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Video_comInterface {
    @FormUrlEncoded
    @POST("video_com/")
    Call<Video_comCreate> getComment(@Header("Authorization") String token,
                                     @Field("video") int video,
                                     @Field("comment") String comment);

    @FormUrlEncoded
    @POST("video_reply/")
    Call<Video_Reply> getReply(@Header("Authorization") String token,
                               @Field("comment_id") int comment_id,
                               @Field("reply_id") int reply_id,
                               @Field("to_uid") int to_uid,
                               @Field("reply_type") int reply_type,
                               @Field("comment") String comment);

    @GET("video_com/")
    Call<Video_com> getPage(@Query("page") String page,
                            @Query("page_size") String page_size);

    @GET("video_com/{id}")
    Call<Video_com> getVideoId(@Header("Authorization") String token, @Path("id") int id);

}
