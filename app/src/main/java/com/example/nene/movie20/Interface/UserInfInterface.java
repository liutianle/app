package com.example.nene.movie20.Interface;


import com.example.nene.movie20.models.User;
import com.example.nene.movie20.models.UserInformation;
import com.example.nene.movie20.models.User_profile;

import org.w3c.dom.Text;

import java.io.File;
import java.lang.ref.SoftReference;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by sam- on 2018/4/24.
 */
public interface UserInfInterface {
    @GET("user/{id}/")
    Call<User> getinformation(@Header("Authorization") String token, @Path("id") String id);

    @Multipart
    @PUT("zwp_userinfo/update/")
    Call<User_profile> getModifyInformation(@Header("Authorization") String token,
                                    @Part("image") String img,
                                    @Part("birth") String birth,
                                    @Part("sex") String sex,
                                    @Part("address") String address,
                                    @Part("nick_name") String nick_name);
}
