package com.example.nene.movie20.Interface;


import com.example.nene.movie20.models.User;
import com.example.nene.movie20.models.UserInformation;
import com.example.nene.movie20.models.User_profile;

import java.lang.ref.SoftReference;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sam- on 2018/4/24.
 */
public interface UserInfInterface {
    @GET("user/{id}/")
    Call<User> getinformation(@Header("Authorization") String token, @Path("id") String id);

    @GET("user/{id}")
    Call<User> getModifyInformation(@Header("Authorization") String token,
                                    @Query("userprofile")User_profile user_profile);
}
