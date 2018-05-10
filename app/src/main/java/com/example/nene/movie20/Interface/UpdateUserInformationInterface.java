package com.example.nene.movie20.Interface;


import com.example.nene.movie20.models.UserInformation;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

/**
 * Created by sam- on 2018/4/24.
 */
public interface UpdateUserInformationInterface {
    @GET("read/")
    Call<UserInformation> getinformation(@Field("id") String id);
}
