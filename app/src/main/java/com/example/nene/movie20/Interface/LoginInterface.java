package com.example.nene.movie20.Interface;


import com.example.nene.movie20.models.Token;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sam- on 2018/4/16.
 */

public interface LoginInterface {
    @FormUrlEncoded
    @POST("login/")
    Call<Token> getCall(@Field("username") String username,
                        @Field("password") String password);
}
