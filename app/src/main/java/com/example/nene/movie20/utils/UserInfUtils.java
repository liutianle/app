package com.example.nene.movie20.utils;

import android.content.SharedPreferences;

import com.example.nene.movie20.Interface.UserInfInterface;
import com.example.nene.movie20.activity.SplashActivity;
import com.example.nene.movie20.models.Constant;
import com.example.nene.movie20.models.User;
import com.example.nene.movie20.models.User_profile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.nene.movie20.models.Constant.BaseUrl;

public class UserInfUtils {
    public static void getUserInf() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UserInfInterface userInfInterface = retrofit.create(UserInfInterface.class);
        System.out.println(SplashActivity.Token);

        final Call<User> call = userInfInterface.getinformation("JWT "+ SplashActivity.Token, "1");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

//    public void modifyUserInf(User_profile user_profile){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constant.BaseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        UserInfInterface userInfInterface = retrofit.create(UserInfInterface.class);
//
//        Call<User> call = userInfInterface.getModifyInformation("JWT" , user_profile);
//
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//        });
//
//    }
}
