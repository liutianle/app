package com.example.nene.movie20.Interface;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserFavVideoInterface {
    @FormUrlEncoded
    @POST("user_favs/")
    Call<Response> getVideoId (@Field("video") int id);

    @FormUrlEncoded
    @POST("user_favs/{id}")
    Call<Response> getDelVideoid(@Path("id") int id);

    @FormUrlEncoded
    @POST("user_favs/")
    Call<Response> getFavVideoList();
}
