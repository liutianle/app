package com.example.nene.movie20.Interface;

import com.example.nene.movie20.models.FavVideo;
import com.example.nene.movie20.models.VideoUrlInf;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserFavVideoInterface {
    @FormUrlEncoded
    @POST("user_favs/")
    Call<String> getVideoId (@Header("Authorization") String token,
                               @Field("video") int id);

    @HTTP(method = "delete", path = "user_favs/{id}/", hasBody = true)
    Call<String> getDelVideoid(@Header("Authorization") String token, @Path("id") int id);

    @GET("user_favs/")
    Call<List<Map<String, VideoUrlInf>>> getFavVideoList(@Header("Authorization") String token);
}
