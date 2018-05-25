package com.example.nene.movie20.Interface;


import com.example.nene.movie20.models.Point;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PointInterface {
    @FormUrlEncoded
    @POST("point/")
    Call<Point> getCommentId(@Header("Authorization") String token,
                             @Field("video_comment") int video_comment,
                             @Field("reply_type") int reply_type);
}
